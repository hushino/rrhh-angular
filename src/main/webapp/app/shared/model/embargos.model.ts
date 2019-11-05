import { Moment } from 'moment';
import { IPersona } from 'app/shared/model/persona.model';

export interface IEmbargos {
  id?: number;
  fecha?: Moment;
  juzgado?: string;
  acreedor?: string;
  cantidad?: string;
  expediente?: string;
  fianzaODeudaPropia?: string;
  origenDeLaDeuda?: string;
  observaciones?: string;
  levantada?: string;
  persona?: IPersona;
}

export class Embargos implements IEmbargos {
  constructor(
    public id?: number,
    public fecha?: Moment,
    public juzgado?: string,
    public acreedor?: string,
    public cantidad?: string,
    public expediente?: string,
    public fianzaODeudaPropia?: string,
    public origenDeLaDeuda?: string,
    public observaciones?: string,
    public levantada?: string,
    public persona?: IPersona
  ) {}
}
