import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RrhhSharedModule } from 'app/shared/shared.module';
import { ConceptoConocimientosEspecialesClasificacionPremiosComponent } from './concepto-conocimientos-especiales-clasificacion-premios.component';
import { ConceptoConocimientosEspecialesClasificacionPremiosDetailComponent } from './concepto-conocimientos-especiales-clasificacion-premios-detail.component';
import { ConceptoConocimientosEspecialesClasificacionPremiosUpdateComponent } from './concepto-conocimientos-especiales-clasificacion-premios-update.component';
import {
  ConceptoConocimientosEspecialesClasificacionPremiosDeletePopupComponent,
  ConceptoConocimientosEspecialesClasificacionPremiosDeleteDialogComponent
} from './concepto-conocimientos-especiales-clasificacion-premios-delete-dialog.component';
import {
  conceptoConocimientosEspecialesClasificacionPremiosRoute,
  conceptoConocimientosEspecialesClasificacionPremiosPopupRoute
} from './concepto-conocimientos-especiales-clasificacion-premios.route';

const ENTITY_STATES = [
  ...conceptoConocimientosEspecialesClasificacionPremiosRoute,
  ...conceptoConocimientosEspecialesClasificacionPremiosPopupRoute
];

@NgModule({
  imports: [RrhhSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ConceptoConocimientosEspecialesClasificacionPremiosComponent,
    ConceptoConocimientosEspecialesClasificacionPremiosDetailComponent,
    ConceptoConocimientosEspecialesClasificacionPremiosUpdateComponent,
    ConceptoConocimientosEspecialesClasificacionPremiosDeleteDialogComponent,
    ConceptoConocimientosEspecialesClasificacionPremiosDeletePopupComponent
  ],
  entryComponents: [ConceptoConocimientosEspecialesClasificacionPremiosDeleteDialogComponent]
})
export class RrhhConceptoConocimientosEspecialesClasificacionPremiosModule {}
