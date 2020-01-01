import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './numeracao.reducer';
import { INumeracao } from 'app/shared/model/numeracao.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface INumeracaoProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Numeracao = (props: INumeracaoProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { numeracaoList, match } = props;
  return (
    <div>
      <h2 id="numeracao-heading">
        Numeracaos
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Numeracao
        </Link>
      </h2>
      <div className="table-responsive">
        {numeracaoList && numeracaoList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Numero</th>
                <th>Ano</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {numeracaoList.map((numeracao, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${numeracao.id}`} color="link" size="sm">
                      {numeracao.id}
                    </Button>
                  </td>
                  <td>{numeracao.numero}</td>
                  <td>{numeracao.ano}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${numeracao.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${numeracao.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${numeracao.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          <div className="alert alert-warning">No Numeracaos found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ numeracao }: IRootState) => ({
  numeracaoList: numeracao.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Numeracao);
