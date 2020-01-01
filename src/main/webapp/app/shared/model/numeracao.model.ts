import { IProtocolo } from 'app/shared/model/protocolo.model';

export interface INumeracao {
  id?: number;
  numero?: number;
  ano?: number;
  protocolos?: IProtocolo[];
}

export const defaultValue: Readonly<INumeracao> = {};
