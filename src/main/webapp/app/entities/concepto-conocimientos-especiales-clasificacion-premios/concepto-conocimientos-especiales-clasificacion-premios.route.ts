import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ConceptoConocimientosEspecialesClasificacionPremios } from 'app/shared/model/concepto-conocimientos-especiales-clasificacion-premios.model';
import { ConceptoConocimientosEspecialesClasificacionPremiosService } from './concepto-conocimientos-especiales-clasificacion-premios.service';
import { ConceptoConocimientosEspecialesClasificacionPremiosComponent } from './concepto-conocimientos-especiales-clasificacion-premios.component';
import { ConceptoConocimientosEspecialesClasificacionPremiosDetailComponent } from './concepto-conocimientos-especiales-clasificacion-premios-detail.component';
import { ConceptoConocimientosEspecialesClasificacionPremiosUpdateComponent } from './concepto-conocimientos-especiales-clasificacion-premios-update.component';
import { ConceptoConocimientosEspecialesClasificacionPremiosDeletePopupComponent } from './concepto-conocimientos-especiales-clasificacion-premios-delete-dialog.component';
import { IConceptoConocimientosEspecialesClasificacionPremios } from 'app/shared/model/concepto-conocimientos-especiales-clasificacion-premios.model';

@Injectable({ providedIn: 'root' })
export class ConceptoConocimientosEspecialesClasificacionPremiosResolve
  implements Resolve<IConceptoConocimientosEspecialesClasificacionPremios> {
  constructor(private service: ConceptoConocimientosEspecialesClasificacionPremiosService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IConceptoConocimientosEspecialesClasificacionPremios> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<ConceptoConocimientosEspecialesClasificacionPremios>) => response.ok),
        map(
          (conceptoConocimientosEspecialesClasificacionPremios: HttpResponse<ConceptoConocimientosEspecialesClasificacionPremios>) =>
            conceptoConocimientosEspecialesClasificacionPremios.body
        )
      );
    }
    return of(new ConceptoConocimientosEspecialesClasificacionPremios());
  }
}

export const conceptoConocimientosEspecialesClasificacionPremiosRoute: Routes = [
  {
    path: '',
    component: ConceptoConocimientosEspecialesClasificacionPremiosComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'rrhhApp.conceptoConocimientosEspecialesClasificacionPremios.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ConceptoConocimientosEspecialesClasificacionPremiosDetailComponent,
    resolve: {
      conceptoConocimientosEspecialesClasificacionPremios: ConceptoConocimientosEspecialesClasificacionPremiosResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rrhhApp.conceptoConocimientosEspecialesClasificacionPremios.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ConceptoConocimientosEspecialesClasificacionPremiosUpdateComponent,
    resolve: {
      conceptoConocimientosEspecialesClasificacionPremios: ConceptoConocimientosEspecialesClasificacionPremiosResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rrhhApp.conceptoConocimientosEspecialesClasificacionPremios.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ConceptoConocimientosEspecialesClasificacionPremiosUpdateComponent,
    resolve: {
      conceptoConocimientosEspecialesClasificacionPremios: ConceptoConocimientosEspecialesClasificacionPremiosResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rrhhApp.conceptoConocimientosEspecialesClasificacionPremios.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const conceptoConocimientosEspecialesClasificacionPremiosPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ConceptoConocimientosEspecialesClasificacionPremiosDeletePopupComponent,
    resolve: {
      conceptoConocimientosEspecialesClasificacionPremios: ConceptoConocimientosEspecialesClasificacionPremiosResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'rrhhApp.conceptoConocimientosEspecialesClasificacionPremios.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
