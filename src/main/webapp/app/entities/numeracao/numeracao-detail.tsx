import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './numeracao.reducer';
import { INumeracao } from 'app/shared/model/numeracao.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface INumeracaoDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const NumeracaoDetail = (props: INumeracaoDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { numeracaoEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Numeracao [<b>{numeracaoEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="numero">Numero</span>
          </dt>
          <dd>{numeracaoEntity.numero}</dd>
          <dt>
            <span id="ano">Ano</span>
          </dt>
          <dd>{numeracaoEntity.ano}</dd>
        </dl>
        <Button tag={Link} to="/numeracao" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/numeracao/${numeracaoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ numeracao }: IRootState) => ({
  numeracaoEntity: numeracao.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(NumeracaoDetail);
