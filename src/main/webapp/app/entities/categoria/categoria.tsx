import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './categoria.reducer';
import { ICategoria } from 'app/shared/model/categoria.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICategoriaProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Categoria = (props: ICategoriaProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { categoriaList, match } = props;
  return (
    <div>
      <h2 id="categoria-heading">
        Categorias
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Categoria
        </Link>
      </h2>
      <div className="table-responsive">
        {categoriaList && categoriaList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Nome Categoria</th>
                <th>Cod Categoria</th>
                <th>Is Active</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {categoriaList.map((categoria, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${categoria.id}`} color="link" size="sm">
                      {categoria.id}
                    </Button>
                  </td>
                  <td>{categoria.nomeCategoria}</td>
                  <td>{categoria.codCategoria}</td>
                  <td>{categoria.isActive ? 'true' : 'false'}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${categoria.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${categoria.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${categoria.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          <div className="alert alert-warning">No Categorias found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ categoria }: IRootState) => ({
  categoriaList: categoria.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Categoria);
