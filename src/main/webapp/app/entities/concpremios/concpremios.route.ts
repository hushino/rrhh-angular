import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Concpremios } from 'app/shared/model/concpremios.model';
import { ConcpremiosService } from './concpremios.service';
import { ConcpremiosComponent } from './concpremios.component';
import { ConcpremiosDetailComponent } from './concpremios-detail.component';
import { ConcpremiosUpdateComponent } from './concpremios-update.component';
import { ConcpremiosDeletePopupComponent } from './concpremios-delete-dialog.component';
import { IConcpremios } from 'app/shared/model/concpremios.model';

@Injectable({ providedIn: 'root' })
export class ConcpremiosResolve implements Resolve<IConcpremios> {
  constructor(private service: ConcpremiosService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IConcpremios> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Concpremios>) => response.ok),
        map((concpremios: HttpResponse<Concpremios>) => concpremios.body)
      );
    }
    return of(new Concpremios());
  }
}

export const concpremiosRoute: Routes = [
  {
    path: '',
    component: ConcpremiosComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'rrhhApp.concpremios.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ConcpremiosDetailComponent,
    resolve: {
      concpremios: ConcpremiosResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rrhhApp.concpremios.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ConcpremiosUpdateComponent,
    resolve: {
      concpremios: ConcpremiosResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rrhhApp.concpremios.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ConcpremiosUpdateComponent,
    resolve: {
      concpremios: ConcpremiosResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rrhhApp.concpremios.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const concpremiosPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ConcpremiosDeletePopupComponent,
    resolve: {
      concpremios: ConcpremiosResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rrhhApp.concpremios.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
