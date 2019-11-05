import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RrhhSharedModule } from 'app/shared/shared.module';
import { AltasAscensosBajasComponent } from './altas-ascensos-bajas.component';
import { AltasAscensosBajasDetailComponent } from './altas-ascensos-bajas-detail.component';
import { AltasAscensosBajasUpdateComponent } from './altas-ascensos-bajas-update.component';
import {
  AltasAscensosBajasDeletePopupComponent,
  AltasAscensosBajasDeleteDialogComponent
} from './altas-ascensos-bajas-delete-dialog.component';
import { altasAscensosBajasRoute, altasAscensosBajasPopupRoute } from './altas-ascensos-bajas.route';

const ENTITY_STATES = [...altasAscensosBajasRoute, ...altasAscensosBajasPopupRoute];

@NgModule({
  imports: [RrhhSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    AltasAscensosBajasComponent,
    AltasAscensosBajasDetailComponent,
    AltasAscensosBajasUpdateComponent,
    AltasAscensosBajasDeleteDialogComponent,
    AltasAscensosBajasDeletePopupComponent
  ],
  entryComponents: [AltasAscensosBajasDeleteDialogComponent]
})
export class RrhhAltasAscensosBajasModule {}
