import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGarantia } from 'app/shared/model/garantia.model';
import { GarantiaService } from './garantia.service';

@Component({
  selector: 'jhi-garantia-delete-dialog',
  templateUrl: './garantia-delete-dialog.component.html'
})
export class GarantiaDeleteDialogComponent {
  garantia: IGarantia;

  constructor(protected garantiaService: GarantiaService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.garantiaService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'garantiaListModification',
        content: 'Deleted an garantia'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-garantia-delete-popup',
  template: ''
})
export class GarantiaDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ garantia }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(GarantiaDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.garantia = garantia;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/garantia', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/garantia', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
