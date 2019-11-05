import { Moment } from 'moment';
import { IPersona } from 'app/shared/model/persona.model';

export interface IConceptoConocimientosEspecialesClasificacionPremios {
  id?: number;
  fecha?: Moment;
  referencias?: string;
  persona?: IPersona;
}

export class ConceptoConocimientosEspecialesClasificacionPremios implements IConceptoConocimientosEspecialesClasificacionPremios {
  constructor(public id?: number, public fecha?: Moment, public referencias?: string, public persona?: IPersona) {}
}
