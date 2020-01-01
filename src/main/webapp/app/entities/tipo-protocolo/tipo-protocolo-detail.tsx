import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './tipo-protocolo.reducer';
import { ITipoProtocolo } from 'app/shared/model/tipo-protocolo.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITipoProtocoloDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TipoProtocoloDetail = (props: ITipoProtocoloDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { tipoProtocoloEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          TipoProtocolo [<b>{tipoProtocoloEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="nomeProtocolo">Nome Protocolo</span>
          </dt>
          <dd>{tipoProtocoloEntity.nomeProtocolo}</dd>
          <dt>
            <span id="codProtocolo">Cod Protocolo</span>
          </dt>
          <dd>{tipoProtocoloEntity.codProtocolo}</dd>
          <dt>
            <span id="isActive">Is Active</span>
          </dt>
          <dd>{tipoProtocoloEntity.isActive ? 'true' : 'false'}</dd>
        </dl>
        <Button tag={Link} to="/tipo-protocolo" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/tipo-protocolo/${tipoProtocoloEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ tipoProtocolo }: IRootState) => ({
  tipoProtocoloEntity: tipoProtocolo.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TipoProtocoloDetail);
