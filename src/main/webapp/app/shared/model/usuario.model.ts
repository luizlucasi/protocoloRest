import { ITipoUsuario } from 'app/shared/model/tipo-usuario.model';

export interface IUsuario {
  id?: number;
  nomeUsuario?: string;
  isActive?: boolean;
  tipoUsuario?: ITipoUsuario;
}

export const defaultValue: Readonly<IUsuario> = {
  isActive: false
};
