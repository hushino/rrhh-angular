import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IConcpremios } from 'app/shared/model/concpremios.model';

type EntityResponseType = HttpResponse<IConcpremios>;
type EntityArrayResponseType = HttpResponse<IConcpremios[]>;

@Injectable({ providedIn: 'root' })
export class ConcpremiosService {
  public resourceUrl = SERVER_API_URL + 'api/concpremios';

  constructor(protected http: HttpClient) {}

  create(concpremios: IConcpremios): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(concpremios);
    return this.http
      .post<IConcpremios>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(concpremios: IConcpremios): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(concpremios);
    return this.http
      .put<IConcpremios>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IConcpremios>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IConcpremios[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(concpremios: IConcpremios): IConcpremios {
    const copy: IConcpremios = Object.assign({}, concpremios, {
      fecha: concpremios.fecha != null && concpremios.fecha.isValid() ? concpremios.fecha.format(DATE_FORMAT) : null
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
      res.body.forEach((concpremios: IConcpremios) => {
        concpremios.fecha = concpremios.fecha != null ? moment(concpremios.fecha) : null;
      });
    }
    return res;
  }
}
