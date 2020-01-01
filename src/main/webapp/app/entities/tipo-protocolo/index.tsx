import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TipoProtocolo from './tipo-protocolo';
import TipoProtocoloDetail from './tipo-protocolo-detail';
import TipoProtocoloUpdate from './tipo-protocolo-update';
import TipoProtocoloDeleteDialog from './tipo-protocolo-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={TipoProtocoloDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TipoProtocoloUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TipoProtocoloUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TipoProtocoloDetail} />
      <ErrorBoundaryRoute path={match.url} component={TipoProtocolo} />
    </Switch>
  </>
);

export default Routes;
