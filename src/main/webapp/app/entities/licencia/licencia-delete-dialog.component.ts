import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILicencia } from 'app/shared/model/licencia.model';
import { LicenciaService } from './licencia.service';

@Component({
  selector: 'jhi-licencia-delete-dialog',
  templateUrl: './licencia-delete-dialog.component.html'
})
export class LicenciaDeleteDialogComponent {
  licencia: ILicencia;

  constructor(protected licenciaService: LicenciaService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.licenciaService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'licenciaListModification',
        content: 'Deleted an licencia'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-licencia-delete-popup',
  template: ''
})
export class LicenciaDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ licencia }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(LicenciaDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.licencia = licencia;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/licencia', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/licencia', { outlets: { popup: null } }]);
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
