import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Versao from './versao';
import VersaoDetail from './versao-detail';
import VersaoUpdate from './versao-update';
import VersaoDeleteDialog from './versao-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={VersaoDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={VersaoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={VersaoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={VersaoDetail} />
      <ErrorBoundaryRoute path={match.url} component={Versao} />
    </Switch>
  </>
);

export default Routes;
