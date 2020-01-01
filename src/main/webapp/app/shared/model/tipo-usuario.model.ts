import { IUsuario } from 'app/shared/model/usuario.model';

export interface ITipoUsuario {
  id?: number;
  descricao?: string;
  usuarios?: IUsuario[];
}

export const defaultValue: Readonly<ITipoUsuario> = {};
