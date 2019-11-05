import { Moment } from 'moment';
import { IPersona } from 'app/shared/model/persona.model';

export interface IOtrosServiciosPrestados {
  id?: number;
  fecha?: Moment;
  referencias?: string;
  persona?: IPersona;
}

export class OtrosServiciosPrestados implements IOtrosServiciosPrestados {
  constructor(public id?: number, public fecha?: Moment, public referencias?: string, public persona?: IPersona) {}
}
