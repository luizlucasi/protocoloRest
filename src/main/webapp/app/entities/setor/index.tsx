import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Setor from './setor';
import SetorDetail from './setor-detail';
import SetorUpdate from './setor-update';
import SetorDeleteDialog from './setor-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={SetorDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SetorUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SetorUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SetorDetail} />
      <ErrorBoundaryRoute path={match.url} component={Setor} />
    </Switch>
  </>
);

export default Routes;
