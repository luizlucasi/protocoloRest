import React, { useState, useEffect } from 'react';
import InfiniteScroll from 'react-infinite-scroller';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities, reset } from './protocolo.reducer';
import { IProtocolo } from 'app/shared/model/protocolo.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface IProtocoloProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Protocolo = (props: IProtocoloProps) => {
  const [paginationState, setPaginationState] = useState(getSortState(props.location, ITEMS_PER_PAGE));
  const [sorting, setSorting] = useState(false);

  const getAllEntities = () => {
    props.getEntities(paginationState.activePage - 1, paginationState.itemsPerPage, `${paginationState.sort},${paginationState.order}`);
  };

  const resetAll = () => {
    props.reset();
    setPaginationState({
      ...paginationState,
      activePage: 1
    });
  };

  useEffect(() => {
    resetAll();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      getAllEntities();
    }
  });

  useEffect(() => {
    getAllEntities();
  }, [paginationState.activePage]);

  const handleLoadMore = () => {
    if (window.pageYOffset > 0) {
      setPaginationState({
        ...paginationState,
        activePage: paginationState.activePage + 1
      });
    }
  };

  useEffect(() => {
    if (sorting) {
      getAllEntities();
      setSorting(false);
    }
  }, [sorting]);

  const sort = p => () => {
    props.reset();
    setPaginationState({
      ...paginationState,
      activePage: 1,
      order: paginationState.order === 'asc' ? 'desc' : 'asc',
      sort: p
    });
    setSorting(true);
  };

  const { protocoloList, match } = props;
  return (
    <div>
      <h2 id="protocolo-heading">
        Protocolos
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp; Create new Protocolo
        </Link>
      </h2>
      <div className="table-responsive">
        <InfiniteScroll
          pageStart={paginationState.activePage}
          loadMore={handleLoadMore}
          hasMore={paginationState.activePage - 1 < props.links.next}
          loader={<div className="loader">Loading ...</div>}
          threshold={0}
          initialLoad={false}
        >
          {protocoloList && protocoloList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th className="hand" onClick={sort('id')}>
                    ID <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('solicitante')}>
                    Solicitante <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('dataSolicitacao')}>
                    Data Solicitacao <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('dataEnvio')}>
                    Data Envio <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('enviadoPor')}>
                    Enviado Por <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('dataCriacao')}>
                    Data Criacao <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('usuarioCriacao')}>
                    Usuario Criacao <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('localizacao')}>
                    Localizacao <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('observacao')}>
                    Observacao <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('nomenclatura')}>
                    Nomenclatura <FontAwesomeIcon icon="sort" />
                  </th>
                  <th className="hand" onClick={sort('formato')}>
                    Formato <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    Versao <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    Documento <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    Tipo Protocolo <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    Setor <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    Categoria <FontAwesomeIcon icon="sort" />
                  </th>
                  <th>
                    Numeracao <FontAwesomeIcon icon="sort" />
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {protocoloList.map((protocolo, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${protocolo.id}`} color="link" size="sm">
                        {protocolo.id}
                      </Button>
                    </td>
                    <td>{protocolo.solicitante}</td>
                    <td>
                      <TextFormat type="date" value={protocolo.dataSolicitacao} format={APP_DATE_FORMAT} />
                    </td>
                    <td>
                      <TextFormat type="date" value={protocolo.dataEnvio} format={APP_DATE_FORMAT} />
                    </td>
                    <td>{protocolo.enviadoPor}</td>
                    <td>
                      <TextFormat type="date" value={protocolo.dataCriacao} format={APP_DATE_FORMAT} />
                    </td>
                    <td>
                      <TextFormat type="date" value={protocolo.usuarioCriacao} format={APP_DATE_FORMAT} />
                    </td>
                    <td>{protocolo.localizacao}</td>
                    <td>{protocolo.observacao}</td>
                    <td>{protocolo.nomenclatura}</td>
                    <td>{protocolo.formato}</td>
                    <td>{protocolo.versao ? <Link to={`versao/${protocolo.versao.id}`}>{protocolo.versao.id}</Link> : ''}</td>
                    <td>{protocolo.documento ? <Link to={`documento/${protocolo.documento.id}`}>{protocolo.documento.id}</Link> : ''}</td>
                    <td>
                      {protocolo.tipoProtocolo ? (
                        <Link to={`tipo-protocolo/${protocolo.tipoProtocolo.id}`}>{protocolo.tipoProtocolo.id}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td>{protocolo.setor ? <Link to={`setor/${protocolo.setor.id}`}>{protocolo.setor.id}</Link> : ''}</td>
                    <td>{protocolo.categoria ? <Link to={`categoria/${protocolo.categoria.id}`}>{protocolo.categoria.id}</Link> : ''}</td>
                    <td>{protocolo.numeracao ? <Link to={`numeracao/${protocolo.numeracao.id}`}>{protocolo.numeracao.id}</Link> : ''}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${protocolo.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${protocolo.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${protocolo.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Protocolos found</div>
          )}
        </InfiniteScroll>
      </div>
    </div>
  );
};

const mapStateToProps = ({ protocolo }: IRootState) => ({
  protocoloList: protocolo.entities,
  totalItems: protocolo.totalItems,
  links: protocolo.links,
  entity: protocolo.entity,
  updateSuccess: protocolo.updateSuccess
});

const mapDispatchToProps = {
  getEntities,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Protocolo);
