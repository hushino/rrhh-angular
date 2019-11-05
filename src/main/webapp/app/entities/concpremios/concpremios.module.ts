import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RrhhSharedModule } from 'app/shared/shared.module';
import { ConcpremiosComponent } from './concpremios.component';
import { ConcpremiosDetailComponent } from './concpremios-detail.component';
import { ConcpremiosUpdateComponent } from './concpremios-update.component';
import { ConcpremiosDeletePopupComponent, ConcpremiosDeleteDialogComponent } from './concpremios-delete-dialog.component';
import { concpremiosRoute, concpremiosPopupRoute } from './concpremios.route';

const ENTITY_STATES = [...concpremiosRoute, ...concpremiosPopupRoute];

@NgModule({
  imports: [RrhhSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ConcpremiosComponent,
    ConcpremiosDetailComponent,
    ConcpremiosUpdateComponent,
    ConcpremiosDeleteDialogComponent,
    ConcpremiosDeletePopupComponent
  ],
  entryComponents: [ConcpremiosDeleteDialogComponent]
})
export class RrhhConcpremiosModule {}
