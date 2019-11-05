import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEmbargos } from 'app/shared/model/embargos.model';

type EntityResponseType = HttpResponse<IEmbargos>;
type EntityArrayResponseType = HttpResponse<IEmbargos[]>;

@Injectable({ providedIn: 'root' })
export class EmbargosService {
  public resourceUrl = SERVER_API_URL + 'api/embargos';

  constructor(protected http: HttpClient) {}

  create(embargos: IEmbargos): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(embargos);
    return this.http
      .post<IEmbargos>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(embargos: IEmbargos): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(embargos);
    return this.http
      .put<IEmbargos>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IEmbargos>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IEmbargos[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(embargos: IEmbargos): IEmbargos {
    const copy: IEmbargos = Object.assign({}, embargos, {
      fecha: embargos.fecha != null && embargos.fecha.isValid() ? embargos.fecha.format(DATE_FORMAT) : null
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
      res.body.forEach((embargos: IEmbargos) => {
        embargos.fecha = embargos.fecha != null ? moment(embargos.fecha) : null;
      });
    }
    return res;
  }
}
