import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './tipo-usuario.reducer';
import { ITipoUsuario } from 'app/shared/model/tipo-usuario.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITipoUsuarioProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const TipoUsuario = (props: ITipoUsuarioProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { tipoUsuarioList, match } = props;
  return (
    <div>
      <h2 id="tipo-usuario-heading">
        Tipo Usuarios
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Tipo Usuario
        </Link>
      </h2>
      <div className="table-responsive">
        {tipoUsuarioList && tipoUsuarioList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Descricao</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {tipoUsuarioList.map((tipoUsuario, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${tipoUsuario.id}`} color="link" size="sm">
                      {tipoUsuario.id}
                    </Button>
                  </td>
                  <td>{tipoUsuario.descricao}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${tipoUsuario.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${tipoUsuario.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${tipoUsuario.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          <div className="alert alert-warning">No Tipo Usuarios found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ tipoUsuario }: IRootState) => ({
  tipoUsuarioList: tipoUsuario.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TipoUsuario);
