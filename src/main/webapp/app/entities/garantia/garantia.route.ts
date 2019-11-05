import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Garantia } from 'app/shared/model/garantia.model';
import { GarantiaService } from './garantia.service';
import { GarantiaComponent } from './garantia.component';
import { GarantiaDetailComponent } from './garantia-detail.component';
import { GarantiaUpdateComponent } from './garantia-update.component';
import { GarantiaDeletePopupComponent } from './garantia-delete-dialog.component';
import { IGarantia } from 'app/shared/model/garantia.model';

@Injectable({ providedIn: 'root' })
export class GarantiaResolve implements Resolve<IGarantia> {
  constructor(private service: GarantiaService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IGarantia> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Garantia>) => response.ok),
        map((garantia: HttpResponse<Garantia>) => garantia.body)
      );
    }
    return of(new Garantia());
  }
}

export const garantiaRoute: Routes = [
  {
    path: '',
    component: GarantiaComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'rrhhApp.garantia.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: GarantiaDetailComponent,
    resolve: {
      garantia: GarantiaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rrhhApp.garantia.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: GarantiaUpdateComponent,
    resolve: {
      garantia: GarantiaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rrhhApp.garantia.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: GarantiaUpdateComponent,
    resolve: {
      garantia: GarantiaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rrhhApp.garantia.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const garantiaPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: GarantiaDeletePopupComponent,
    resolve: {
      garantia: GarantiaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rrhhApp.garantia.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
