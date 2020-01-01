import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
import sessions, { SessionsState } from 'app/modules/account/sessions/sessions.reducer';
// prettier-ignore
import categoria, {
  CategoriaState
} from 'app/entities/categoria/categoria.reducer';
// prettier-ignore
import documento, {
  DocumentoState
} from 'app/entities/documento/documento.reducer';
// prettier-ignore
import grupoSolicitante, {
  GrupoSolicitanteState
} from 'app/entities/grupo-solicitante/grupo-solicitante.reducer';
// prettier-ignore
import numeracao, {
  NumeracaoState
} from 'app/entities/numeracao/numeracao.reducer';
// prettier-ignore
import setor, {
  SetorState
} from 'app/entities/setor/setor.reducer';
// prettier-ignore
import tipoProtocolo, {
  TipoProtocoloState
} from 'app/entities/tipo-protocolo/tipo-protocolo.reducer';
// prettier-ignore
import tipoUsuario, {
  TipoUsuarioState
} from 'app/entities/tipo-usuario/tipo-usuario.reducer';
// prettier-ignore
import usuario, {
  UsuarioState
} from 'app/entities/usuario/usuario.reducer';
// prettier-ignore
import versao, {
  VersaoState
} from 'app/entities/versao/versao.reducer';
// prettier-ignore
import protocolo, {
  ProtocoloState
} from 'app/entities/protocolo/protocolo.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly sessions: SessionsState;
  readonly categoria: CategoriaState;
  readonly documento: DocumentoState;
  readonly grupoSolicitante: GrupoSolicitanteState;
  readonly numeracao: NumeracaoState;
  readonly setor: SetorState;
  readonly tipoProtocolo: TipoProtocoloState;
  readonly tipoUsuario: TipoUsuarioState;
  readonly usuario: UsuarioState;
  readonly versao: VersaoState;
  readonly protocolo: ProtocoloState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  sessions,
  categoria,
  documento,
  grupoSolicitante,
  numeracao,
  setor,
  tipoProtocolo,
  tipoUsuario,
  usuario,
  versao,
  protocolo,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar
});

export default rootReducer;
