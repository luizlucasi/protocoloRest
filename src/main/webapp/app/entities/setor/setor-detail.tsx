import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './setor.reducer';
import { ISetor } from 'app/shared/model/setor.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISetorDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const SetorDetail = (props: ISetorDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { setorEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Setor [<b>{setorEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="nomeSetor">Nome Setor</span>
          </dt>
          <dd>{setorEntity.nomeSetor}</dd>
          <dt>
            <span id="codigoSetor">Codigo Setor</span>
          </dt>
          <dd>{setorEntity.codigoSetor}</dd>
          <dt>
            <span id="isActive">Is Active</span>
          </dt>
          <dd>{setorEntity.isActive ? 'true' : 'false'}</dd>
        </dl>
        <Button tag={Link} to="/setor" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/setor/${setorEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ setor }: IRootState) => ({
  setorEntity: setor.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(SetorDetail);
