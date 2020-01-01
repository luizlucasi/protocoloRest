import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Categoria from './categoria';
import Documento from './documento';
import GrupoSolicitante from './grupo-solicitante';
import Numeracao from './numeracao';
import Setor from './setor';
import TipoProtocolo from './tipo-protocolo';
import TipoUsuario from './tipo-usuario';
import Usuario from './usuario';
import Versao from './versao';
import Protocolo from './protocolo';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}categoria`} component={Categoria} />
      <ErrorBoundaryRoute path={`${match.url}documento`} component={Documento} />
      <ErrorBoundaryRoute path={`${match.url}grupo-solicitante`} component={GrupoSolicitante} />
      <ErrorBoundaryRoute path={`${match.url}numeracao`} component={Numeracao} />
      <ErrorBoundaryRoute path={`${match.url}setor`} component={Setor} />
      <ErrorBoundaryRoute path={`${match.url}tipo-protocolo`} component={TipoProtocolo} />
      <ErrorBoundaryRoute path={`${match.url}tipo-usuario`} component={TipoUsuario} />
      <ErrorBoundaryRoute path={`${match.url}usuario`} component={Usuario} />
      <ErrorBoundaryRoute path={`${match.url}versao`} component={Versao} />
      <ErrorBoundaryRoute path={`${match.url}protocolo`} component={Protocolo} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
