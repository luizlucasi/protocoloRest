import { Moment } from 'moment';
import { IVersao } from 'app/shared/model/versao.model';
import { IDocumento } from 'app/shared/model/documento.model';
import { ITipoProtocolo } from 'app/shared/model/tipo-protocolo.model';
import { ISetor } from 'app/shared/model/setor.model';
import { ICategoria } from 'app/shared/model/categoria.model';
import { INumeracao } from 'app/shared/model/numeracao.model';
import { Formato } from 'app/shared/model/enumerations/formato.model';

export interface IProtocolo {
  id?: number;
  solicitante?: string;
  dataSolicitacao?: Moment;
  dataEnvio?: Moment;
  enviadoPor?: string;
  dataCriacao?: Moment;
  usuarioCriacao?: Moment;
  localizacao?: string;
  observacao?: string;
  nomenclatura?: string;
  formato?: Formato;
  versao?: IVersao;
  documento?: IDocumento;
  tipoProtocolo?: ITipoProtocolo;
  setor?: ISetor;
  categoria?: ICategoria;
  numeracao?: INumeracao;
}

export const defaultValue: Readonly<IProtocolo> = {};
