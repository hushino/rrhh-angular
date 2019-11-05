import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEmbargos } from 'app/shared/model/embargos.model';
import { EmbargosService } from './embargos.service';

@Component({
  selector: 'jhi-embargos-delete-dialog',
  templateUrl: './embargos-delete-dialog.component.html'
})
export class EmbargosDeleteDialogComponent {
  embargos: IEmbargos;

  constructor(protected embargosService: EmbargosService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.embargosService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'embargosListModification',
        content: 'Deleted an embargos'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-embargos-delete-popup',
  template: ''
})
export class EmbargosDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ embargos }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(EmbargosDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.embargos = embargos;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/embargos', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/embargos', { outlets: { popup: null } }]);
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
