import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IOtrosServiciosPrestados } from 'app/shared/model/otros-servicios-prestados.model';

type EntityResponseType = HttpResponse<IOtrosServiciosPrestados>;
type EntityArrayResponseType = HttpResponse<IOtrosServiciosPrestados[]>;

@Injectable({ providedIn: 'root' })
export class OtrosServiciosPrestadosService {
  public resourceUrl = SERVER_API_URL + 'api/otros-servicios-prestados';

  constructor(protected http: HttpClient) {}

  create(otrosServiciosPrestados: IOtrosServiciosPrestados): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(otrosServiciosPrestados);
    return this.http
      .post<IOtrosServiciosPrestados>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(otrosServiciosPrestados: IOtrosServiciosPrestados): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(otrosServiciosPrestados);
    return this.http
      .put<IOtrosServiciosPrestados>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IOtrosServiciosPrestados>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IOtrosServiciosPrestados[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(otrosServiciosPrestados: IOtrosServiciosPrestados): IOtrosServiciosPrestados {
    const copy: IOtrosServiciosPrestados = Object.assign({}, otrosServiciosPrestados, {
      fecha:
        otrosServiciosPrestados.fecha != null && otrosServiciosPrestados.fecha.isValid()
          ? otrosServiciosPrestados.fecha.format(DATE_FORMAT)
          : null
    });
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
      res.body.forEach((otrosServiciosPrestados: IOtrosServiciosPrestados) => {
        otrosServiciosPrestados.fecha = otrosServiciosPrestados.fecha != null ? moment(otrosServiciosPrestados.fecha) : null;
      });
    }
    return res;
  }
}
