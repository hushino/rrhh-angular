import { Moment } from 'moment';
import { IPersona } from 'app/shared/model/persona.model';

export interface IAltasAscensosBajas {
  id?: number;
  fecha?: Moment;
  cargo?: string;
  observaciones?: string;
  expediente?: string;
  prestaservicioen?: string;
  persona?: IPersona;
}

export class AltasAscensosBajas implements IAltasAscensosBajas {
  constructor(
    public id?: number,
    public fecha?: Moment,
    public cargo?: string,
    public observaciones?: string,
    public expediente?: string,
    public prestaservicioen?: string,
    public persona?: IPersona
  ) {}
}
