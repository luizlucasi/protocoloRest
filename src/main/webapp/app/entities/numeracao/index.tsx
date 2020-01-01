import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Numeracao from './numeracao';
import NumeracaoDetail from './numeracao-detail';
import NumeracaoUpdate from './numeracao-update';
import NumeracaoDeleteDialog from './numeracao-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={NumeracaoDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={NumeracaoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={NumeracaoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={NumeracaoDetail} />
      <ErrorBoundaryRoute path={match.url} component={Numeracao} />
    </Switch>
  </>
);

export default Routes;
