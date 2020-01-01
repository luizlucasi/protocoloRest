import { IProtocolo } from 'app/shared/model/protocolo.model';

export interface ICategoria {
  id?: number;
  nomeCategoria?: string;
  codCategoria?: string;
  isActive?: boolean;
  protocolos?: IProtocolo[];
}

export const defaultValue: Readonly<ICategoria> = {
  isActive: false
};
