import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Licencia } from 'app/shared/model/licencia.model';
import { LicenciaService } from './licencia.service';
import { LicenciaComponent } from './licencia.component';
import { LicenciaDetailComponent } from './licencia-detail.component';
import { LicenciaUpdateComponent } from './licencia-update.component';
import { LicenciaDeletePopupComponent } from './licencia-delete-dialog.component';
import { ILicencia } from 'app/shared/model/licencia.model';

@Injectable({ providedIn: 'root' })
export class LicenciaResolve implements Resolve<ILicencia> {
  constructor(private service: LicenciaService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ILicencia> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Licencia>) => response.ok),
        map((licencia: HttpResponse<Licencia>) => licencia.body)
      );
    }
    return of(new Licencia());
  }
}

export const licenciaRoute: Routes = [
  {
    path: '',
    component: LicenciaComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'rrhhApp.licencia.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: LicenciaDetailComponent,
    resolve: {
      licencia: LicenciaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rrhhApp.licencia.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: LicenciaUpdateComponent,
    resolve: {
      licencia: LicenciaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rrhhApp.licencia.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: LicenciaUpdateComponent,
    resolve: {
      licencia: LicenciaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rrhhApp.licencia.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const licenciaPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: LicenciaDeletePopupComponent,
    resolve: {
      licencia: LicenciaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rrhhApp.licencia.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
