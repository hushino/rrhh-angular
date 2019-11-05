import { Moment } from 'moment';
import { IPersona } from 'app/shared/model/persona.model';

export interface IPenasDisciplinariasSufridas {
  id?: number;
  fecha?: Moment;
  expediente?: string;
  referencias?: string;
  persona?: IPersona;
}

export class PenasDisciplinariasSufridas implements IPenasDisciplinariasSufridas {
  constructor(
    public id?: number,
    public fecha?: Moment,
    public expediente?: string,
    public referencias?: string,
    public persona?: IPersona
  ) {}
}
