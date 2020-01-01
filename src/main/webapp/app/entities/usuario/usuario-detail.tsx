import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './usuario.reducer';
import { IUsuario } from 'app/shared/model/usuario.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IUsuarioDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const UsuarioDetail = (props: IUsuarioDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { usuarioEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Usuario [<b>{usuarioEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="nomeUsuario">Nome Usuario</span>
          </dt>
          <dd>{usuarioEntity.nomeUsuario}</dd>
          <dt>
            <span id="isActive">Is Active</span>
          </dt>
          <dd>{usuarioEntity.isActive ? 'true' : 'false'}</dd>
          <dt>Tipo Usuario</dt>
          <dd>{usuarioEntity.tipoUsuario ? usuarioEntity.tipoUsuario.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/usuario" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/usuario/${usuarioEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ usuario }: IRootState) => ({
  usuarioEntity: usuario.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(UsuarioDetail);
