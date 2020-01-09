import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import NumeroProtocolo from './numero-protocolo';
import NumeroProtocoloDetail from './numero-protocolo-detail';
import NumeroProtocoloUpdate from './numero-protocolo-update';
import NumeroProtocoloDeleteDialog from './numero-protocolo-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={NumeroProtocoloDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={NumeroProtocoloUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={NumeroProtocoloUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={NumeroProtocoloDetail} />
      <ErrorBoundaryRoute path={match.url} component={NumeroProtocolo} />
    </Switch>
  </>
);

export default Routes;
