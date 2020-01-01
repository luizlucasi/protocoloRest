import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './grupo-solicitante.reducer';
import { IGrupoSolicitante } from 'app/shared/model/grupo-solicitante.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IGrupoSolicitanteProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const GrupoSolicitante = (props: IGrupoSolicitanteProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { grupoSolicitanteList, match } = props;
  return (
    <div>
      <h2 id="grupo-solicitante-heading">
        Grupo Solicitantes
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Grupo Solicitante
        </Link>
      </h2>
      <div className="table-responsive">
        {grupoSolicitanteList && grupoSolicitanteList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Nome Grupo Solicitante</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {grupoSolicitanteList.map((grupoSolicitante, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${grupoSolicitante.id}`} color="link" size="sm">
                      {grupoSolicitante.id}
                    </Button>
                  </td>
                  <td>{grupoSolicitante.nomeGrupoSolicitante}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${grupoSolicitante.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${grupoSolicitante.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${grupoSolicitante.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          <div className="alert alert-warning">No Grupo Solicitantes found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ grupoSolicitante }: IRootState) => ({
  grupoSolicitanteList: grupoSolicitante.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(GrupoSolicitante);
