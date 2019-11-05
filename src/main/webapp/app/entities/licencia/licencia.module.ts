import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RrhhSharedModule } from 'app/shared/shared.module';
import { LicenciaComponent } from './licencia.component';
import { LicenciaDetailComponent } from './licencia-detail.component';
import { LicenciaUpdateComponent } from './licencia-update.component';
import { LicenciaDeletePopupComponent, LicenciaDeleteDialogComponent } from './licencia-delete-dialog.component';
import { licenciaRoute, licenciaPopupRoute } from './licencia.route';

const ENTITY_STATES = [...licenciaRoute, ...licenciaPopupRoute];

@NgModule({
  imports: [RrhhSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    LicenciaComponent,
    LicenciaDetailComponent,
    LicenciaUpdateComponent,
    LicenciaDeleteDialogComponent,
    LicenciaDeletePopupComponent
  ],
  entryComponents: [LicenciaDeleteDialogComponent]
})
export class RrhhLicenciaModule {}
