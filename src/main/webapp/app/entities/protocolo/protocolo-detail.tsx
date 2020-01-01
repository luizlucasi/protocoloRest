import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './protocolo.reducer';
import { IProtocolo } from 'app/shared/model/protocolo.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IProtocoloDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ProtocoloDetail = (props: IProtocoloDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { protocoloEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Protocolo [<b>{protocoloEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="solicitante">Solicitante</span>
          </dt>
          <dd>{protocoloEntity.solicitante}</dd>
          <dt>
            <span id="dataSolicitacao">Data Solicitacao</span>
          </dt>
          <dd>
            <TextFormat value={protocoloEntity.dataSolicitacao} type="date" format={APP_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="dataEnvio">Data Envio</span>
          </dt>
          <dd>
            <TextFormat value={protocoloEntity.dataEnvio} type="date" format={APP_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="enviadoPor">Enviado Por</span>
          </dt>
          <dd>{protocoloEntity.enviadoPor}</dd>
          <dt>
            <span id="dataCriacao">Data Criacao</span>
          </dt>
          <dd>
            <TextFormat value={protocoloEntity.dataCriacao} type="date" format={APP_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="usuarioCriacao">Usuario Criacao</span>
          </dt>
          <dd>
            <TextFormat value={protocoloEntity.usuarioCriacao} type="date" format={APP_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="localizacao">Localizacao</span>
          </dt>
          <dd>{protocoloEntity.localizacao}</dd>
          <dt>
            <span id="observacao">Observacao</span>
          </dt>
          <dd>{protocoloEntity.observacao}</dd>
          <dt>
            <span id="nomenclatura">Nomenclatura</span>
          </dt>
          <dd>{protocoloEntity.nomenclatura}</dd>
          <dt>
            <span id="formato">Formato</span>
          </dt>
          <dd>{protocoloEntity.formato}</dd>
          <dt>Versao</dt>
          <dd>{protocoloEntity.versao ? protocoloEntity.versao.id : ''}</dd>
          <dt>Documento</dt>
          <dd>{protocoloEntity.documento ? protocoloEntity.documento.id : ''}</dd>
          <dt>Tipo Protocolo</dt>
          <dd>{protocoloEntity.tipoProtocolo ? protocoloEntity.tipoProtocolo.id : ''}</dd>
          <dt>Setor</dt>
          <dd>{protocoloEntity.setor ? protocoloEntity.setor.id : ''}</dd>
          <dt>Categoria</dt>
          <dd>{protocoloEntity.categoria ? protocoloEntity.categoria.id : ''}</dd>
          <dt>Numeracao</dt>
          <dd>{protocoloEntity.numeracao ? protocoloEntity.numeracao.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/protocolo" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/protocolo/${protocoloEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ protocolo }: IRootState) => ({
  protocoloEntity: protocolo.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ProtocoloDetail);
