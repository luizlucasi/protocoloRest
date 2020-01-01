import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Protocolo from './protocolo';
import ProtocoloDetail from './protocolo-detail';
import ProtocoloUpdate from './protocolo-update';
import ProtocoloDeleteDialog from './protocolo-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ProtocoloDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ProtocoloUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ProtocoloUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ProtocoloDetail} />
      <ErrorBoundaryRoute path={match.url} component={Protocolo} />
    </Switch>
  </>
);

export default Routes;
