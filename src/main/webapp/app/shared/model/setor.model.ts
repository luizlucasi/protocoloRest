import { IProtocolo } from 'app/shared/model/protocolo.model';

export interface ISetor {
  id?: number;
  nomeSetor?: string;
  codigoSetor?: string;
  isActive?: boolean;
  protocolos?: IProtocolo[];
}

export const defaultValue: Readonly<ISetor> = {
  isActive: false
};
