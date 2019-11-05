import { Moment } from 'moment';
import { IPersona } from 'app/shared/model/persona.model';

export interface IGarantia {
  id?: number;
  presentadaFecha?: Moment;
  garantia?: string;
  observaciones?: string;
  persona?: IPersona;
}

export class Garantia implements IGarantia {
  constructor(
    public id?: number,
    public presentadaFecha?: Moment,
    public garantia?: string,
    public observaciones?: string,
    public persona?: IPersona
  ) {}
}
