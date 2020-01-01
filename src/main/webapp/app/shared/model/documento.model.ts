import { IProtocolo } from 'app/shared/model/protocolo.model';

export interface IDocumento {
  id?: number;
  nomeDocumento?: string;
  codDocumento?: string;
  isActive?: boolean;
  protocolos?: IProtocolo[];
}

export const defaultValue: Readonly<IDocumento> = {
  isActive: false
};
