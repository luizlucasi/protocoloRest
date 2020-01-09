import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './numero-protocolo.reducer';
import { INumeroProtocolo } from 'app/shared/model/numero-protocolo.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface INumeroProtocoloDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const NumeroProtocoloDetail = (props: INumeroProtocoloDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { numeroProtocoloEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          NumeroProtocolo [<b>{numeroProtocoloEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="ano">Ano</span>
          </dt>
          <dd>{numeroProtocoloEntity.ano}</dd>
          <dt>
            <span id="numero">Numero</span>
          </dt>
          <dd>{numeroProtocoloEntity.numero}</dd>
          <dt>Protocolo</dt>
          <dd>{numeroProtocoloEntity.protocolo ? numeroProtocoloEntity.protocolo.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/numero-protocolo" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/numero-protocolo/${numeroProtocoloEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ numeroProtocolo }: IRootState) => ({
  numeroProtocoloEntity: numeroProtocolo.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(NumeroProtocoloDetail);
