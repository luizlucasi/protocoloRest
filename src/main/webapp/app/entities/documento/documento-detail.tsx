import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './documento.reducer';
import { IDocumento } from 'app/shared/model/documento.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDocumentoDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DocumentoDetail = (props: IDocumentoDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { documentoEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Documento [<b>{documentoEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="nomeDocumento">Nome Documento</span>
          </dt>
          <dd>{documentoEntity.nomeDocumento}</dd>
          <dt>
            <span id="codDocumento">Cod Documento</span>
          </dt>
          <dd>{documentoEntity.codDocumento}</dd>
          <dt>
            <span id="isActive">Is Active</span>
          </dt>
          <dd>{documentoEntity.isActive ? 'true' : 'false'}</dd>
        </dl>
        <Button tag={Link} to="/documento" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/documento/${documentoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ documento }: IRootState) => ({
  documentoEntity: documento.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DocumentoDetail);
