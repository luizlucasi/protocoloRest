import { IProtocolo } from 'app/shared/model/protocolo.model';

export interface INumeroProtocolo {
  id?: number;
  ano?: string;
  numero?: number;
  protocolo?: IProtocolo;
}

export const defaultValue: Readonly<INumeroProtocolo> = {};
