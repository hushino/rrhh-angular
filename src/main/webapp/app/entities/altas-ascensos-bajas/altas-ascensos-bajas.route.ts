import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AltasAscensosBajas } from 'app/shared/model/altas-ascensos-bajas.model';
import { AltasAscensosBajasService } from './altas-ascensos-bajas.service';
import { AltasAscensosBajasComponent } from './altas-ascensos-bajas.component';
import { AltasAscensosBajasDetailComponent } from './altas-ascensos-bajas-detail.component';
import { AltasAscensosBajasUpdateComponent } from './altas-ascensos-bajas-update.component';
import { AltasAscensosBajasDeletePopupComponent } from './altas-ascensos-bajas-delete-dialog.component';
import { IAltasAscensosBajas } from 'app/shared/model/altas-ascensos-bajas.model';

@Injectable({ providedIn: 'root' })
export class AltasAscensosBajasResolve implements Resolve<IAltasAscensosBajas> {
  constructor(private service: AltasAscensosBajasService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IAltasAscensosBajas> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<AltasAscensosBajas>) => response.ok),
        map((altasAscensosBajas: HttpResponse<AltasAscensosBajas>) => altasAscensosBajas.body)
      );
    }
    return of(new AltasAscensosBajas());
  }
}

export const altasAscensosBajasRoute: Routes = [
  {
    path: '',
    component: AltasAscensosBajasComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'rrhhApp.altasAscensosBajas.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: AltasAscensosBajasDetailComponent,
    resolve: {
      altasAscensosBajas: AltasAscensosBajasResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rrhhApp.altasAscensosBajas.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: AltasAscensosBajasUpdateComponent,
    resolve: {
      altasAscensosBajas: AltasAscensosBajasResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rrhhApp.altasAscensosBajas.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: AltasAscensosBajasUpdateComponent,
    resolve: {
      altasAscensosBajas: AltasAscensosBajasResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rrhhApp.altasAscensosBajas.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const altasAscensosBajasPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: AltasAscensosBajasDeletePopupComponent,
    resolve: {
      altasAscensosBajas: AltasAscensosBajasResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rrhhApp.altasAscensosBajas.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
