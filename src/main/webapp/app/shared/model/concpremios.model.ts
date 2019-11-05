import { Moment } from 'moment';
import { IPersona } from 'app/shared/model/persona.model';

export interface IConcpremios {
  id?: number;
  fecha?: Moment;
  referencias?: string;
  persona?: IPersona;
}

export class Concpremios implements IConcpremios {
  constructor(public id?: number, public fecha?: Moment, public referencias?: string, public persona?: IPersona) {}
}
