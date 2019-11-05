import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PenasDisciplinariasSufridas } from 'app/shared/model/penas-disciplinarias-sufridas.model';
import { PenasDisciplinariasSufridasService } from './penas-disciplinarias-sufridas.service';
import { PenasDisciplinariasSufridasComponent } from './penas-disciplinarias-sufridas.component';
import { PenasDisciplinariasSufridasDetailComponent } from './penas-disciplinarias-sufridas-detail.component';
import { PenasDisciplinariasSufridasUpdateComponent } from './penas-disciplinarias-sufridas-update.component';
import { PenasDisciplinariasSufridasDeletePopupComponent } from './penas-disciplinarias-sufridas-delete-dialog.component';
import { IPenasDisciplinariasSufridas } from 'app/shared/model/penas-disciplinarias-sufridas.model';

@Injectable({ providedIn: 'root' })
export class PenasDisciplinariasSufridasResolve implements Resolve<IPenasDisciplinariasSufridas> {
  constructor(private service: PenasDisciplinariasSufridasService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IPenasDisciplinariasSufridas> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<PenasDisciplinariasSufridas>) => response.ok),
        map((penasDisciplinariasSufridas: HttpResponse<PenasDisciplinariasSufridas>) => penasDisciplinariasSufridas.body)
      );
    }
    return of(new PenasDisciplinariasSufridas());
  }
}

export const penasDisciplinariasSufridasRoute: Routes = [
  {
    path: '',
    component: PenasDisciplinariasSufridasComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'rrhhApp.penasDisciplinariasSufridas.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PenasDisciplinariasSufridasDetailComponent,
    resolve: {
      penasDisciplinariasSufridas: PenasDisciplinariasSufridasResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rrhhApp.penasDisciplinariasSufridas.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PenasDisciplinariasSufridasUpdateComponent,
    resolve: {
      penasDisciplinariasSufridas: PenasDisciplinariasSufridasResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rrhhApp.penasDisciplinariasSufridas.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PenasDisciplinariasSufridasUpdateComponent,
    resolve: {
      penasDisciplinariasSufridas: PenasDisciplinariasSufridasResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rrhhApp.penasDisciplinariasSufridas.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const penasDisciplinariasSufridasPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: PenasDisciplinariasSufridasDeletePopupComponent,
    resolve: {
      penasDisciplinariasSufridas: PenasDisciplinariasSufridasResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rrhhApp.penasDisciplinariasSufridas.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
