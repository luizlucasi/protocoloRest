import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import GrupoSolicitante from './grupo-solicitante';
import GrupoSolicitanteDetail from './grupo-solicitante-detail';
import GrupoSolicitanteUpdate from './grupo-solicitante-update';
import GrupoSolicitanteDeleteDialog from './grupo-solicitante-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={GrupoSolicitanteDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={GrupoSolicitanteUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={GrupoSolicitanteUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={GrupoSolicitanteDetail} />
      <ErrorBoundaryRoute path={match.url} component={GrupoSolicitante} />
    </Switch>
  </>
);

export default Routes;
