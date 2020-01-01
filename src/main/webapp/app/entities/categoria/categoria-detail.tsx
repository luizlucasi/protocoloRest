import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './categoria.reducer';
import { ICategoria } from 'app/shared/model/categoria.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICategoriaDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CategoriaDetail = (props: ICategoriaDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { categoriaEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Categoria [<b>{categoriaEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="nomeCategoria">Nome Categoria</span>
          </dt>
          <dd>{categoriaEntity.nomeCategoria}</dd>
          <dt>
            <span id="codCategoria">Cod Categoria</span>
          </dt>
          <dd>{categoriaEntity.codCategoria}</dd>
          <dt>
            <span id="isActive">Is Active</span>
          </dt>
          <dd>{categoriaEntity.isActive ? 'true' : 'false'}</dd>
        </dl>
        <Button tag={Link} to="/categoria" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/categoria/${categoriaEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ categoria }: IRootState) => ({
  categoriaEntity: categoria.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CategoriaDetail);
