import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPenasDisciplinariasSufridas } from 'app/shared/model/penas-disciplinarias-sufridas.model';

type EntityResponseType = HttpResponse<IPenasDisciplinariasSufridas>;
type EntityArrayResponseType = HttpResponse<IPenasDisciplinariasSufridas[]>;

@Injectable({ providedIn: 'root' })
export class PenasDisciplinariasSufridasService {
  public resourceUrl = SERVER_API_URL + 'api/penas-disciplinarias-sufridas';

  constructor(protected http: HttpClient) {}

  create(penasDisciplinariasSufridas: IPenasDisciplinariasSufridas): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(penasDisciplinariasSufridas);
    return this.http
      .post<IPenasDisciplinariasSufridas>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(penasDisciplinariasSufridas: IPenasDisciplinariasSufridas): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(penasDisciplinariasSufridas);
    return this.http
      .put<IPenasDisciplinariasSufridas>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IPenasDisciplinariasSufridas>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IPenasDisciplinariasSufridas[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(penasDisciplinariasSufridas: IPenasDisciplinariasSufridas): IPenasDisciplinariasSufridas {
    const copy: IPenasDisciplinariasSufridas = Object.assign({}, penasDisciplinariasSufridas, {
      fecha:
        penasDisciplinariasSufridas.fecha != null && penasDisciplinariasSufridas.fecha.isValid()
          ? penasDisciplinariasSufridas.fecha.format(DATE_FORMAT)
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
      res.body.forEach((penasDisciplinariasSufridas: IPenasDisciplinariasSufridas) => {
        penasDisciplinariasSufridas.fecha = penasDisciplinariasSufridas.fecha != null ? moment(penasDisciplinariasSufridas.fecha) : null;
      });
    }
    return res;
  }
}
