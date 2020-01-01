import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './documento.reducer';
import { IDocumento } from 'app/shared/model/documento.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDocumentoProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Documento = (props: IDocumentoProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { documentoList, match } = props;
  return (
    <div>
      <h2 id="documento-heading">
        Documentos
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Documento
        </Link>
      </h2>
      <div className="table-responsive">
        {documentoList && documentoList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Nome Documento</th>
                <th>Cod Documento</th>
                <th>Is Active</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {documentoList.map((documento, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${documento.id}`} color="link" size="sm">
                      {documento.id}
                    </Button>
                  </td>
                  <td>{documento.nomeDocumento}</td>
                  <td>{documento.codDocumento}</td>
                  <td>{documento.isActive ? 'true' : 'false'}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${documento.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${documento.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${documento.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          <div className="alert alert-warning">No Documentos found</div>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ documento }: IRootState) => ({
  documentoList: documento.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Documento);
