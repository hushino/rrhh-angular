import { Moment } from 'moment';
import { ILicencia } from 'app/shared/model/licencia.model';
import { IAltasAscensosBajas } from 'app/shared/model/altas-ascensos-bajas.model';
import { IConceptoConocimientosEspecialesClasificacionPremios } from 'app/shared/model/concepto-conocimientos-especiales-clasificacion-premios.model';
import { IEmbargos } from 'app/shared/model/embargos.model';
import { IGarantia } from 'app/shared/model/garantia.model';
import { IOtrosServiciosPrestados } from 'app/shared/model/otros-servicios-prestados.model';
import { IPenasDisciplinariasSufridas } from 'app/shared/model/penas-disciplinarias-sufridas.model';

export interface IPersona {
  id?: number;
  nombre?: string;
  apellido?: string;
  cuil?: string;
  dni?: number;
  legajo?: number;
  apodo?: string;
  fotoContentType?: string;
  foto?: any;
  soltero?: string;
  casado?: string;
  conviviente?: string;
  viudo?: string;
  domicilio?: string;
  lugar?: string;
  calle?: string;
  numero?: string;
  telefonofijo?: string;
  numerodecelular?: string;
  oficioprofecion?: string;
  niveldeestudios?: string;
  gruposanguineo?: string;
  factor?: string;
  donante?: string;
  diabetes?: string;
  hipertension?: string;
  alergias?: string;
  asma?: string;
  otros?: string;
  fechadeingreso?: Moment;
  instrumentolegal?: string;
  categoria?: string;
  item?: string;
  planta?: string;
  area?: string;
  direccion?: string;
  annos?: number;
  meses?: number;
  dias?: number;
  realizocomputodeservicios?: string;
  poseeconocimientoenmaquinasviales?: string;
  casoemergenciacelular?: string;
  casoemergenciafijo?: string;
  casoemergencianombre?: string;
  casoemergenciacelular2?: string;
  casoemergenciafijo2?: string;
  casoemergencianombre2?: string;
  familiaracargonombre?: string;
  familiaracargonombre2?: string;
  familiaracargonombre3?: string;
  familiaracargonombre4?: string;
  familiaracargonombre5?: string;
  familiaracargodni?: string;
  familiaracargodni2?: string;
  familiaracargodni3?: string;
  familiaracargodni4?: string;
  familiaracargodni5?: string;
  familiaracargoedad?: string;
  familiaracargoedad2?: string;
  familiaracargoedad3?: string;
  familiaracargoedad4?: string;
  familiaracargoedad5?: string;
  altura?: string;
  barrio?: string;
  estudiosincompletos?: string;
  conyugeapellido?: string;
  conyugenombre?: string;
  conyugedni?: number;
  conyugecuil?: string;
  grupofamiliarapellidonombre?: string;
  grupofamiliarapellidonombre2?: string;
  grupofamiliarapellidonombre3?: string;
  grupofamiliarapellidonombre4?: string;
  grupofamiliarapellidonombre5?: string;
  grupofamiliarapellidonombre6?: string;
  grupofamiliarapellidonombre7?: string;
  grupofamiliarapellidonombre8?: string;
  grupofamiliarapellidonombre9?: string;
  grupofamiliarapellidonombre10?: string;
  grupofamiliarapellidonombre11?: string;
  grupofamiliarapellidonombreedad?: string;
  grupofamiliarapellidonombreedad2?: string;
  grupofamiliarapellidonombreedad3?: string;
  grupofamiliarapellidonombreedad4?: string;
  grupofamiliarapellidonombreedad5?: string;
  grupofamiliarapellidonombreedad6?: string;
  grupofamiliarapellidonombreedad7?: string;
  grupofamiliarapellidonombreedad8?: string;
  grupofamiliarapellidonombreedad9?: string;
  grupofamiliarapellidonombreedad10?: string;
  grupofamiliarapellidonombreedad11?: string;
  grupofamiliarapellidonombredni?: number;
  grupofamiliarapellidonombredni2?: number;
  grupofamiliarapellidonombredni3?: number;
  grupofamiliarapellidonombredni4?: number;
  grupofamiliarapellidonombredni5?: number;
  grupofamiliarapellidonombredni6?: number;
  grupofamiliarapellidonombredni7?: number;
  grupofamiliarapellidonombredni8?: number;
  grupofamiliarapellidonombredni9?: number;
  grupofamiliarapellidonombredni10?: number;
  grupofamiliarapellidonombredni11?: number;
  grupofamiliarapellidonombrefamiliar?: string;
  grupofamiliarapellidonombrefamiliar2?: string;
  grupofamiliarapellidonombrefamiliar4?: string;
  grupofamiliarapellidonombrefamiliar3?: string;
  grupofamiliarapellidonombrefamiliar5?: string;
  grupofamiliarapellidonombrefamiliar6?: string;
  grupofamiliarapellidonombrefamiliar7?: string;
  grupofamiliarapellidonombrefamiliar8?: string;
  grupofamiliarapellidonombrefamiliar9?: string;
  grupofamiliarapellidonombrefamiliar10?: string;
  grupofamiliarapellidonombrefamiliar11?: string;
  licencias?: ILicencia[];
  altasAscensosBajas?: IAltasAscensosBajas[];
  conceptoConocimientosEspecialesClasificacionPremios?: IConceptoConocimientosEspecialesClasificacionPremios[];
  embargos?: IEmbargos[];
  garantias?: IGarantia[];
  otrosServiciosPrestados?: IOtrosServiciosPrestados[];
  penasDisciplinariasSufridas?: IPenasDisciplinariasSufridas[];
}

export class Persona implements IPersona {
  constructor(
    public id?: number,
    public nombre?: string,
    public apellido?: string,
    public cuil?: string,
    public dni?: number,
    public legajo?: number,
    public apodo?: string,
    public fotoContentType?: string,
    public foto?: any,
    public soltero?: string,
    public casado?: string,
    public conviviente?: string,
    public viudo?: string,
    public domicilio?: string,
    public lugar?: string,
    public calle?: string,
    public numero?: string,
    public telefonofijo?: string,
    public numerodecelular?: string,
    public oficioprofecion?: string,
    public niveldeestudios?: string,
    public gruposanguineo?: string,
    public factor?: string,
    public donante?: string,
    public diabetes?: string,
    public hipertension?: string,
    public alergias?: string,
    public asma?: string,
    public otros?: string,
    public fechadeingreso?: Moment,
    public instrumentolegal?: string,
    public categoria?: string,
    public item?: string,
    public planta?: string,
    public area?: string,
    public direccion?: string,
    public annos?: number,
    public meses?: number,
    public dias?: number,
    public realizocomputodeservicios?: string,
    public poseeconocimientoenmaquinasviales?: string,
    public casoemergenciacelular?: string,
    public casoemergenciafijo?: string,
    public casoemergencianombre?: string,
    public casoemergenciacelular2?: string,
    public casoemergenciafijo2?: string,
    public casoemergencianombre2?: string,
    public familiaracargonombre?: string,
    public familiaracargonombre2?: string,
    public familiaracargonombre3?: string,
    public familiaracargonombre4?: string,
    public familiaracargonombre5?: string,
    public familiaracargodni?: string,
    public familiaracargodni2?: string,
    public familiaracargodni3?: string,
    public familiaracargodni4?: string,
    public familiaracargodni5?: string,
    public familiaracargoedad?: string,
    public familiaracargoedad2?: string,
    public familiaracargoedad3?: string,
    public familiaracargoedad4?: string,
    public familiaracargoedad5?: string,
    public altura?: string,
    public barrio?: string,
    public estudiosincompletos?: string,
    public conyugeapellido?: string,
    public conyugenombre?: string,
    public conyugedni?: number,
    public conyugecuil?: string,
    public grupofamiliarapellidonombre?: string,
    public grupofamiliarapellidonombre2?: string,
    public grupofamiliarapellidonombre3?: string,
    public grupofamiliarapellidonombre4?: string,
    public grupofamiliarapellidonombre5?: string,
    public grupofamiliarapellidonombre6?: string,
    public grupofamiliarapellidonombre7?: string,
    public grupofamiliarapellidonombre8?: string,
    public grupofamiliarapellidonombre9?: string,
    public grupofamiliarapellidonombre10?: string,
    public grupofamiliarapellidonombre11?: string,
    public grupofamiliarapellidonombreedad?: string,
    public grupofamiliarapellidonombreedad2?: string,
    public grupofamiliarapellidonombreedad3?: string,
    public grupofamiliarapellidonombreedad4?: string,
    public grupofamiliarapellidonombreedad5?: string,
    public grupofamiliarapellidonombreedad6?: string,
    public grupofamiliarapellidonombreedad7?: string,
    public grupofamiliarapellidonombreedad8?: string,
    public grupofamiliarapellidonombreedad9?: string,
    public grupofamiliarapellidonombreedad10?: string,
    public grupofamiliarapellidonombreedad11?: string,
    public grupofamiliarapellidonombredni?: number,
    public grupofamiliarapellidonombredni2?: number,
    public grupofamiliarapellidonombredni3?: number,
    public grupofamiliarapellidonombredni4?: number,
    public grupofamiliarapellidonombredni5?: number,
    public grupofamiliarapellidonombredni6?: number,
    public grupofamiliarapellidonombredni7?: number,
    public grupofamiliarapellidonombredni8?: number,
    public grupofamiliarapellidonombredni9?: number,
    public grupofamiliarapellidonombredni10?: number,
    public grupofamiliarapellidonombredni11?: number,
    public grupofamiliarapellidonombrefamiliar?: string,
    public grupofamiliarapellidonombrefamiliar2?: string,
    public grupofamiliarapellidonombrefamiliar4?: string,
    public grupofamiliarapellidonombrefamiliar3?: string,
    public grupofamiliarapellidonombrefamiliar5?: string,
    public grupofamiliarapellidonombrefamiliar6?: string,
    public grupofamiliarapellidonombrefamiliar7?: string,
    public grupofamiliarapellidonombrefamiliar8?: string,
    public grupofamiliarapellidonombrefamiliar9?: string,
    public grupofamiliarapellidonombrefamiliar10?: string,
    public grupofamiliarapellidonombrefamiliar11?: string,
    public licencias?: ILicencia[],
    public altasAscensosBajas?: IAltasAscensosBajas[],
    public conceptoConocimientosEspecialesClasificacionPremios?: IConceptoConocimientosEspecialesClasificacionPremios[],
    public embargos?: IEmbargos[],
    public garantias?: IGarantia[],
    public otrosServiciosPrestados?: IOtrosServiciosPrestados[],
    public penasDisciplinariasSufridas?: IPenasDisciplinariasSufridas[]
  ) {}
}
