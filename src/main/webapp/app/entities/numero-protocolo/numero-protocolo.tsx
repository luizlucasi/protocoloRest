import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './numero-protocolo.reducer';
import { INumeroProtocolo } from 'app/shared/model/numero-protocolo.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface INumeroProtocoloProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const NumeroProtocolo = (props: INumeroProtocoloProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { numeroProtocoloList, match } = props;
  return (
    <div>
      <h2 id="numero-protocolo-heading">
        Numero Protocolos
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Numero Protocolo
        </Link>
      </h2>
      <div className="table-responsive">
        {numeroProtocoloList && numeroProtocoloList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Ano</th>
                <th>Numero</th>
                <th>Protocolo</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {numeroProtocoloList.map((numeroProtocolo, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${numeroProtocolo.id}`} color="link" size="sm">
                      {numeroProtocolo.id}
                    </Button>
                  </td>
                  <td>{numeroProtocolo.ano}</td>
                  <td>{numeroProtocolo.numero}</td>
                  <td>
                    {numeroProtocolo.protocolo ? (
                      <Link to={`protocolo/${numeroProtocolo.protocolo.id}`}>{numeroProtocolo.protocolo.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${numeroProtocolo.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${numeroProtocolo.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${numeroProtocolo.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          <div className="alert alert-warning">No Numero Protocolos found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ numeroProtocolo }: IRootState) => ({
  numeroProtocoloList: numeroProtocolo.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(NumeroProtocolo);
