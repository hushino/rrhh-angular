import { Moment } from 'moment';
import { IPersona } from 'app/shared/model/persona.model';

export interface ILicencia {
  id?: number;
  fechaLicencia?: Moment;
  referencias?: string;
  numeroDeDias?: string;
  observaciones?: string;
  usuariosMod?: string;
  persona?: IPersona;
}

export class Licencia implements ILicencia {
  constructor(
    public id?: number,
    public fechaLicencia?: Moment,
    public referencias?: string,
    public numeroDeDias?: string,
    public observaciones?: string,
    public usuariosMod?: string,
    public persona?: IPersona
  ) {}
}
