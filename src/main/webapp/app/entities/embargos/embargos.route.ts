import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Embargos } from 'app/shared/model/embargos.model';
import { EmbargosService } from './embargos.service';
import { EmbargosComponent } from './embargos.component';
import { EmbargosDetailComponent } from './embargos-detail.component';
import { EmbargosUpdateComponent } from './embargos-update.component';
import { EmbargosDeletePopupComponent } from './embargos-delete-dialog.component';
import { IEmbargos } from 'app/shared/model/embargos.model';

@Injectable({ providedIn: 'root' })
export class EmbargosResolve implements Resolve<IEmbargos> {
  constructor(private service: EmbargosService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IEmbargos> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Embargos>) => response.ok),
        map((embargos: HttpResponse<Embargos>) => embargos.body)
      );
    }
    return of(new Embargos());
  }
}

export const embargosRoute: Routes = [
  {
    path: '',
    component: EmbargosComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'rrhhApp.embargos.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EmbargosDetailComponent,
    resolve: {
      embargos: EmbargosResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rrhhApp.embargos.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EmbargosUpdateComponent,
    resolve: {
      embargos: EmbargosResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rrhhApp.embargos.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EmbargosUpdateComponent,
    resolve: {
      embargos: EmbargosResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rrhhApp.embargos.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const embargosPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: EmbargosDeletePopupComponent,
    resolve: {
      embargos: EmbargosResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rrhhApp.embargos.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
