import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './numero-protocolo.reducer';
import { INumeroProtocolo } from 'app/shared/model/numero-protocolo.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface INumeroProtocoloUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const NumeroProtocoloUpdate = (props: INumeroProtocoloUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { numeroProtocoloEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/numero-protocolo');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...numeroProtocoloEntity,
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
          <h2 id="protocoloRestApp.numeroProtocolo.home.createOrEditLabel">Create or edit a NumeroProtocolo</h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : numeroProtocoloEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="numero-protocolo-id">ID</Label>
                  <AvInput id="numero-protocolo-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="anoLabel" for="numero-protocolo-ano">
                  Ano
                </Label>
                <AvField
                  id="numero-protocolo-ano"
                  type="text"
                  name="ano"
                  validate={{
                    required: { value: true, errorMessage: 'This field is required.' }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="numeroLabel" for="numero-protocolo-numero">
                  Numero
                </Label>
                <AvField
                  id="numero-protocolo-numero"
                  type="string"
                  className="form-control"
                  name="numero"
                  validate={{
                    required: { value: true, errorMessage: 'This field is required.' },
                    number: { value: true, errorMessage: 'This field should be a number.' }
                  }}
                />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/numero-protocolo" replace color="info">
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
  numeroProtocoloEntity: storeState.numeroProtocolo.entity,
  loading: storeState.numeroProtocolo.loading,
  updating: storeState.numeroProtocolo.updating,
  updateSuccess: storeState.numeroProtocolo.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(NumeroProtocoloUpdate);
