import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPersona } from 'app/shared/model/persona.model';

import 'rxjs/add/operator/distinctUntilChanged';

import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/map';

type EntityResponseType = HttpResponse<IPersona>;
type EntityArrayResponseType = HttpResponse<IPersona[]>;

@Injectable({ providedIn: 'root' })
export class PersonaService {
  public resourceUrl = SERVER_API_URL + 'api/personas';

  constructor(protected http: HttpClient) { }

  create(persona: IPersona): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(persona);
    return this.http
      .post<IPersona>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(persona: IPersona): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(persona);
    return this.http
      .put<IPersona>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IPersona>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }


  search(terms: Observable<string>) {
    return terms
      .distinctUntilChanged()
      .switchMap(term => this.searchEntries(term));
  }

  /* searchEntries(term) {
    return this.http
      .get<IPersona>(`${this.resourceUrl}?dni.equals=${term}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  } */
  searchEntries(term) {
    return this.http
      .get(`${this.resourceUrl}?nombre.equals=${term}`)
      .map(res => res.toString());
  }


  /* 
    findbydni(dni: any): Observable<EntityResponseType> {
      return this.http
        .get<IPersona>(`${this.resourceUrl}?dni.equals=${dni}`, { observe: 'response' })
        .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    } */

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IPersona[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(persona: IPersona): IPersona {
    const copy: IPersona = Object.assign({}, persona, {
      fechadeingreso: persona.fechadeingreso != null && persona.fechadeingreso.isValid() ? persona.fechadeingreso.format(DATE_FORMAT) : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.fechadeingreso = res.body.fechadeingreso != null ? moment(res.body.fechadeingreso) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((persona: IPersona) => {
        persona.fechadeingreso = persona.fechadeingreso != null ? moment(persona.fechadeingreso) : null;
      });
    }
    return res;
  }
}
