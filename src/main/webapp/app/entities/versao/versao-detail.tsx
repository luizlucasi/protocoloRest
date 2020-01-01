import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './versao.reducer';
import { IVersao } from 'app/shared/model/versao.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IVersaoDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const VersaoDetail = (props: IVersaoDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { versaoEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          Versao [<b>{versaoEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="numeroVersao">Numero Versao</span>
          </dt>
          <dd>{versaoEntity.numeroVersao}</dd>
        </dl>
        <Button tag={Link} to="/versao" replace color="info">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/versao/${versaoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ versao }: IRootState) => ({
  versaoEntity: versao.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(VersaoDetail);
