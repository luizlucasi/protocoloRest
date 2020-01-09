import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { INumeroProtocolo } from 'app/shared/model/numero-protocolo.model';
import { getEntities as getNumeroProtocolos } from 'app/entities/numero-protocolo/numero-protocolo.reducer';
import { IVersao } from 'app/shared/model/versao.model';
import { getEntities as getVersaos } from 'app/entities/versao/versao.reducer';
import { IDocumento } from 'app/shared/model/documento.model';
import { getEntities as getDocumentos } from 'app/entities/documento/documento.reducer';
import { ITipoProtocolo } from 'app/shared/model/tipo-protocolo.model';
import { getEntities as getTipoProtocolos } from 'app/entities/tipo-protocolo/tipo-protocolo.reducer';
import { ISetor } from 'app/shared/model/setor.model';
import { getEntities as getSetors } from 'app/entities/setor/setor.reducer';
import { ICategoria } from 'app/shared/model/categoria.model';
import { getEntities as getCategorias } from 'app/entities/categoria/categoria.reducer';
import { INumeracao } from 'app/shared/model/numeracao.model';
import { getEntities as getNumeracaos } from 'app/entities/numeracao/numeracao.reducer';
import { getEntity, updateEntity, createEntity, reset } from './protocolo.reducer';
import { IProtocolo } from 'app/shared/model/protocolo.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IProtocoloUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ProtocoloUpdate = (props: IProtocoloUpdateProps) => {
  const [numProtocoloId, setNumProtocoloId] = useState('0');
  const [versaoId, setVersaoId] = useState('0');
  const [documentoId, setDocumentoId] = useState('0');
  const [tipoProtocoloId, setTipoProtocoloId] = useState('0');
  const [setorId, setSetorId] = useState('0');
  const [categoriaId, setCategoriaId] = useState('0');
  const [numeracaoId, setNumeracaoId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const {
    protocoloEntity,
    numeroProtocolos,
    versaos,
    documentos,
    tipoProtocolos,
    setors,
    categorias,
    numeracaos,
    loading,
    updating
  } = props;

  const handleClose = () => {
    props.history.push('/protocolo');
  };

  useEffect(() => {
    if (!isNew) {
      props.getEntity(props.match.params.id);
    }

    props.getNumeroProtocolos();
    props.getVersaos();
    props.getDocumentos();
    props.getTipoProtocolos();
    props.getSetors();
    props.getCategorias();
    props.getNumeracaos();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    values.dataSolicitacao = convertDateTimeToServer(values.dataSolicitacao);
    values.dataEnvio = convertDateTimeToServer(values.dataEnvio);
    values.dataCriacao = convertDateTimeToServer(values.dataCriacao);
    values.usuarioCriacao = convertDateTimeToServer(values.usuarioCriacao);

    if (errors.length === 0) {
      const entity = {
        ...protocoloEntity,
        ...values
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="protocoloRestApp.protocolo.home.createOrEditLabel">Create or edit a Protocolo</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : protocoloEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="protocolo-id">ID</Label>
                  <AvInput id="protocolo-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="solicitanteLabel" for="protocolo-solicitante">
                  Solicitante
                </Label>
                <AvField
                  id="protocolo-solicitante"
                  type="text"
                  name="solicitante"
                  validate={{
                    required: { value: true, errorMessage: 'This field is required.' }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="dataSolicitacaoLabel" for="protocolo-dataSolicitacao">
                  Data Solicitacao
                </Label>
                <AvInput
                  id="protocolo-dataSolicitacao"
                  type="datetime-local"
                  className="form-control"
                  name="dataSolicitacao"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? null : convertDateTimeFromServer(props.protocoloEntity.dataSolicitacao)}
                  validate={{
                    required: { value: true, errorMessage: 'This field is required.' }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="dataEnvioLabel" for="protocolo-dataEnvio">
                  Data Envio
                </Label>
                <AvInput
                  id="protocolo-dataEnvio"
                  type="datetime-local"
                  className="form-control"
                  name="dataEnvio"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? null : convertDateTimeFromServer(props.protocoloEntity.dataEnvio)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="enviadoPorLabel" for="protocolo-enviadoPor">
                  Enviado Por
                </Label>
                <AvField id="protocolo-enviadoPor" type="text" name="enviadoPor" />
              </AvGroup>
              <AvGroup>
                <Label id="dataCriacaoLabel" for="protocolo-dataCriacao">
                  Data Criacao
                </Label>
                <AvInput
                  id="protocolo-dataCriacao"
                  type="datetime-local"
                  className="form-control"
                  name="dataCriacao"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? null : convertDateTimeFromServer(props.protocoloEntity.dataCriacao)}
                  validate={{
                    required: { value: true, errorMessage: 'This field is required.' }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="usuarioCriacaoLabel" for="protocolo-usuarioCriacao">
                  Usuario Criacao
                </Label>
                <AvInput
                  id="protocolo-usuarioCriacao"
                  type="datetime-local"
                  className="form-control"
                  name="usuarioCriacao"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? null : convertDateTimeFromServer(props.protocoloEntity.usuarioCriacao)}
                  validate={{
                    required: { value: true, errorMessage: 'This field is required.' }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="localizacaoLabel" for="protocolo-localizacao">
                  Localizacao
                </Label>
                <AvField id="protocolo-localizacao" type="text" name="localizacao" />
              </AvGroup>
              <AvGroup>
                <Label id="observacaoLabel" for="protocolo-observacao">
                  Observacao
                </Label>
                <AvField id="protocolo-observacao" type="text" name="observacao" />
              </AvGroup>
              <AvGroup>
                <Label id="nomenclaturaLabel" for="protocolo-nomenclatura">
                  Nomenclatura
                </Label>
                <AvField id="protocolo-nomenclatura" type="text" name="nomenclatura" />
              </AvGroup>
              <AvGroup>
                <Label id="formatoLabel" for="protocolo-formato">
                  Formato
                </Label>
                <AvInput
                  id="protocolo-formato"
                  type="select"
                  className="form-control"
                  name="formato"
                  value={(!isNew && protocoloEntity.formato) || 'D'}
                >
                  <option value="D">D</option>
                  <option value="F">F</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="protocolo-numProtocolo">Num Protocolo</Label>
                <AvInput id="protocolo-numProtocolo" type="select" className="form-control" name="numProtocolo.id">
                  <option value="" key="0" />
                  {numeroProtocolos
                    ? numeroProtocolos.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.numProtocolo}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="protocolo-versao">Versao</Label>
                <AvInput id="protocolo-versao" type="select" className="form-control" name="versao.id">
                  <option value="" key="0" />
                  {versaos
                    ? versaos.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="protocolo-documento">Documento</Label>
                <AvInput id="protocolo-documento" type="select" className="form-control" name="documento.id">
                  <option value="" key="0" />
                  {documentos
                    ? documentos.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="protocolo-tipoProtocolo">Tipo Protocolo</Label>
                <AvInput id="protocolo-tipoProtocolo" type="select" className="form-control" name="tipoProtocolo.id">
                  <option value="" key="0" />
                  {tipoProtocolos
                    ? tipoProtocolos.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="protocolo-setor">Setor</Label>
                <AvInput id="protocolo-setor" type="select" className="form-control" name="setor.id">
                  <option value="" key="0" />
                  {setors
                    ? setors.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="protocolo-categoria">Categoria</Label>
                <AvInput id="protocolo-categoria" type="select" className="form-control" name="categoria.id">
                  <option value="" key="0" />
                  {categorias
                    ? categorias.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="protocolo-numeracao">Numeracao</Label>
                <AvInput id="protocolo-numeracao" type="select" className="form-control" name="numeracao.id">
                  <option value="" key="0" />
                  {numeracaos
                    ? numeracaos.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/protocolo" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  numeroProtocolos: storeState.numeroProtocolo.entities,
  versaos: storeState.versao.entities,
  documentos: storeState.documento.entities,
  tipoProtocolos: storeState.tipoProtocolo.entities,
  setors: storeState.setor.entities,
  categorias: storeState.categoria.entities,
  numeracaos: storeState.numeracao.entities,
  protocoloEntity: storeState.protocolo.entity,
  loading: storeState.protocolo.loading,
  updating: storeState.protocolo.updating,
  updateSuccess: storeState.protocolo.updateSuccess
});

const mapDispatchToProps = {
  getNumeroProtocolos,
  getVersaos,
  getDocumentos,
  getTipoProtocolos,
  getSetors,
  getCategorias,
  getNumeracaos,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ProtocoloUpdate);
