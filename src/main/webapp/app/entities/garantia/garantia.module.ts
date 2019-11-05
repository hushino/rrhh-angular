import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RrhhSharedModule } from 'app/shared/shared.module';
import { GarantiaComponent } from './garantia.component';
import { GarantiaDetailComponent } from './garantia-detail.component';
import { GarantiaUpdateComponent } from './garantia-update.component';
import { GarantiaDeletePopupComponent, GarantiaDeleteDialogComponent } from './garantia-delete-dialog.component';
import { garantiaRoute, garantiaPopupRoute } from './garantia.route';

const ENTITY_STATES = [...garantiaRoute, ...garantiaPopupRoute];

@NgModule({
  imports: [RrhhSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    GarantiaComponent,
    GarantiaDetailComponent,
    GarantiaUpdateComponent,
    GarantiaDeleteDialogComponent,
    GarantiaDeletePopupComponent
  ],
  entryComponents: [GarantiaDeleteDialogComponent]
})
export class RrhhGarantiaModule {}
