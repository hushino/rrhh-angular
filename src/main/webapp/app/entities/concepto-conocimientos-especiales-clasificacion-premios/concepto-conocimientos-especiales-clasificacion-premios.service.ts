import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IConceptoConocimientosEspecialesClasificacionPremios } from 'app/shared/model/concepto-conocimientos-especiales-clasificacion-premios.model';

type EntityResponseType = HttpResponse<IConceptoConocimientosEspecialesClasificacionPremios>;
type EntityArrayResponseType = HttpResponse<IConceptoConocimientosEspecialesClasificacionPremios[]>;

@Injectable({ providedIn: 'root' })
export class ConceptoConocimientosEspecialesClasificacionPremiosService {
  public resourceUrl = SERVER_API_URL + 'api/concepto-conocimientos-especiales-clasificacion-premios';

  constructor(protected http: HttpClient) {}

  create(
    conceptoConocimientosEspecialesClasificacionPremios: IConceptoConocimientosEspecialesClasificacionPremios
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(conceptoConocimientosEspecialesClasificacionPremios);
    return this.http
      .post<IConceptoConocimientosEspecialesClasificacionPremios>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(
    conceptoConocimientosEspecialesClasificacionPremios: IConceptoConocimientosEspecialesClasificacionPremios
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(conceptoConocimientosEspecialesClasificacionPremios);
    return this.http
      .put<IConceptoConocimientosEspecialesClasificacionPremios>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IConceptoConocimientosEspecialesClasificacionPremios>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IConceptoConocimientosEspecialesClasificacionPremios[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(
    conceptoConocimientosEspecialesClasificacionPremios: IConceptoConocimientosEspecialesClasificacionPremios
  ): IConceptoConocimientosEspecialesClasificacionPremios {
    const copy: IConceptoConocimientosEspecialesClasificacionPremios = Object.assign(
      {},
      conceptoConocimientosEspecialesClasificacionPremios,
      {
        fecha:
          conceptoConocimientosEspecialesClasificacionPremios.fecha != null &&
          conceptoConocimientosEspecialesClasificacionPremios.fecha.isValid()
            ? conceptoConocimientosEspecialesClasificacionPremios.fecha.format(DATE_FORMAT)
            : null
      }
    );
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.fecha = res.body.fecha != null ? moment(res.body.fecha) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((conceptoConocimientosEspecialesClasificacionPremios: IConceptoConocimientosEspecialesClasificacionPremios) => {
        conceptoConocimientosEspecialesClasificacionPremios.fecha =
          conceptoConocimientosEspecialesClasificacionPremios.fecha != null
            ? moment(conceptoConocimientosEspecialesClasificacionPremios.fecha)
            : null;
      });
    }
    return res;
  }
}
