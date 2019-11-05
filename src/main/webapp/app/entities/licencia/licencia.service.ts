import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ILicencia } from 'app/shared/model/licencia.model';

type EntityResponseType = HttpResponse<ILicencia>;
type EntityArrayResponseType = HttpResponse<ILicencia[]>;

@Injectable({ providedIn: 'root' })
export class LicenciaService {
  public resourceUrl = SERVER_API_URL + 'api/licencias';

  constructor(protected http: HttpClient) {}

  create(licencia: ILicencia): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(licencia);
    return this.http
      .post<ILicencia>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(licencia: ILicencia): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(licencia);
    return this.http
      .put<ILicencia>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ILicencia>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ILicencia[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(licencia: ILicencia): ILicencia {
    const copy: ILicencia = Object.assign({}, licencia, {
      fechaLicencia: licencia.fechaLicencia != null && licencia.fechaLicencia.isValid() ? licencia.fechaLicencia.format(DATE_FORMAT) : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.fechaLicencia = res.body.fechaLicencia != null ? moment(res.body.fechaLicencia) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((licencia: ILicencia) => {
        licencia.fechaLicencia = licencia.fechaLicencia != null ? moment(licencia.fechaLicencia) : null;
      });
    }
    return res;
  }
}
