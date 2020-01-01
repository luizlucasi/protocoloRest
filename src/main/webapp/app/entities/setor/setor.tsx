import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './setor.reducer';
import { ISetor } from 'app/shared/model/setor.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISetorProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Setor = (props: ISetorProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { setorList, match } = props;
  return (
    <div>
      <h2 id="setor-heading">
        Setors
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Setor
        </Link>
      </h2>
      <div className="table-responsive">
        {setorList && setorList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Nome Setor</th>
                <th>Codigo Setor</th>
                <th>Is Active</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {setorList.map((setor, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${setor.id}`} color="link" size="sm">
                      {setor.id}
                    </Button>
                  </td>
                  <td>{setor.nomeSetor}</td>
                  <td>{setor.codigoSetor}</td>
                  <td>{setor.isActive ? 'true' : 'false'}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${setor.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${setor.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${setor.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          <div className="alert alert-warning">No Setors found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ setor }: IRootState) => ({
  setorList: setor.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Setor);
