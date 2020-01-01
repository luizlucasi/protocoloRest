import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './grupo-solicitante.reducer';
import { IGrupoSolicitante } from 'app/shared/model/grupo-solicitante.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IGrupoSolicitanteDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const GrupoSolicitanteDetail = (props: IGrupoSolicitanteDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { grupoSolicitanteEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          GrupoSolicitante [<b>{grupoSolicitanteEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="nomeGrupoSolicitante">Nome Grupo Solicitante</span>
          </dt>
          <dd>{grupoSolicitanteEntity.nomeGrupoSolicitante}</dd>
        </dl>
        <Button tag={Link} to="/grupo-solicitante" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/grupo-solicitante/${grupoSolicitanteEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ grupoSolicitante }: IRootState) => ({
  grupoSolicitanteEntity: grupoSolicitante.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(GrupoSolicitanteDetail);
