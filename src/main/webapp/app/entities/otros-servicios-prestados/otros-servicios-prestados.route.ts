import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { OtrosServiciosPrestados } from 'app/shared/model/otros-servicios-prestados.model';
import { OtrosServiciosPrestadosService } from './otros-servicios-prestados.service';
import { OtrosServiciosPrestadosComponent } from './otros-servicios-prestados.component';
import { OtrosServiciosPrestadosDetailComponent } from './otros-servicios-prestados-detail.component';
import { OtrosServiciosPrestadosUpdateComponent } from './otros-servicios-prestados-update.component';
import { OtrosServiciosPrestadosDeletePopupComponent } from './otros-servicios-prestados-delete-dialog.component';
import { IOtrosServiciosPrestados } from 'app/shared/model/otros-servicios-prestados.model';

@Injectable({ providedIn: 'root' })
export class OtrosServiciosPrestadosResolve implements Resolve<IOtrosServiciosPrestados> {
  constructor(private service: OtrosServiciosPrestadosService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IOtrosServiciosPrestados> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<OtrosServiciosPrestados>) => response.ok),
        map((otrosServiciosPrestados: HttpResponse<OtrosServiciosPrestados>) => otrosServiciosPrestados.body)
      );
    }
    return of(new OtrosServiciosPrestados());
  }
}

export const otrosServiciosPrestadosRoute: Routes = [
  {
    path: '',
    component: OtrosServiciosPrestadosComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'rrhhApp.otrosServiciosPrestados.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: OtrosServiciosPrestadosDetailComponent,
    resolve: {
      otrosServiciosPrestados: OtrosServiciosPrestadosResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rrhhApp.otrosServiciosPrestados.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: OtrosServiciosPrestadosUpdateComponent,
    resolve: {
      otrosServiciosPrestados: OtrosServiciosPrestadosResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rrhhApp.otrosServiciosPrestados.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: OtrosServiciosPrestadosUpdateComponent,
    resolve: {
      otrosServiciosPrestados: OtrosServiciosPrestadosResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rrhhApp.otrosServiciosPrestados.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const otrosServiciosPrestadosPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: OtrosServiciosPrestadosDeletePopupComponent,
    resolve: {
      otrosServiciosPrestados: OtrosServiciosPrestadosResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rrhhApp.otrosServiciosPrestados.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
