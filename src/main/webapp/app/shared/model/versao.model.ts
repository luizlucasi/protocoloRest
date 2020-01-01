import { IProtocolo } from 'app/shared/model/protocolo.model';

export interface IVersao {
  id?: number;
  numeroVersao?: string;
  protocolos?: IProtocolo[];
}

export const defaultValue: Readonly<IVersao> = {};
