import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RrhhSharedModule } from 'app/shared/shared.module';
import { OtrosServiciosPrestadosComponent } from './otros-servicios-prestados.component';
import { OtrosServiciosPrestadosDetailComponent } from './otros-servicios-prestados-detail.component';
import { OtrosServiciosPrestadosUpdateComponent } from './otros-servicios-prestados-update.component';
import {
  OtrosServiciosPrestadosDeletePopupComponent,
  OtrosServiciosPrestadosDeleteDialogComponent
} from './otros-servicios-prestados-delete-dialog.component';
import { otrosServiciosPrestadosRoute, otrosServiciosPrestadosPopupRoute } from './otros-servicios-prestados.route';

const ENTITY_STATES = [...otrosServiciosPrestadosRoute, ...otrosServiciosPrestadosPopupRoute];

@NgModule({
  imports: [RrhhSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    OtrosServiciosPrestadosComponent,
    OtrosServiciosPrestadosDetailComponent,
    OtrosServiciosPrestadosUpdateComponent,
    OtrosServiciosPrestadosDeleteDialogComponent,
    OtrosServiciosPrestadosDeletePopupComponent
  ],
  entryComponents: [OtrosServiciosPrestadosDeleteDialogComponent]
})
export class RrhhOtrosServiciosPrestadosModule {}
