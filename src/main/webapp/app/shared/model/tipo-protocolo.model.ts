import { IProtocolo } from 'app/shared/model/protocolo.model';

export interface ITipoProtocolo {
  id?: number;
  nomeProtocolo?: string;
  codProtocolo?: string;
  isActive?: boolean;
  protocolos?: IProtocolo[];
}

export const defaultValue: Readonly<ITipoProtocolo> = {
  isActive: false
};
