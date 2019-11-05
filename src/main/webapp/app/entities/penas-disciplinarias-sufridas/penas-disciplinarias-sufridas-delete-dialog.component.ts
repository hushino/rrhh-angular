import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPenasDisciplinariasSufridas } from 'app/shared/model/penas-disciplinarias-sufridas.model';
import { PenasDisciplinariasSufridasService } from './penas-disciplinarias-sufridas.service';

@Component({
  selector: 'jhi-penas-disciplinarias-sufridas-delete-dialog',
  templateUrl: './penas-disciplinarias-sufridas-delete-dialog.component.html'
})
export class PenasDisciplinariasSufridasDeleteDialogComponent {
  penasDisciplinariasSufridas: IPenasDisciplinariasSufridas;

  constructor(
    protected penasDisciplinariasSufridasService: PenasDisciplinariasSufridasService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.penasDisciplinariasSufridasService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'penasDisciplinariasSufridasListModification',
        content: 'Deleted an penasDisciplinariasSufridas'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-penas-disciplinarias-sufridas-delete-popup',
  template: ''
})
export class PenasDisciplinariasSufridasDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ penasDisciplinariasSufridas }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(PenasDisciplinariasSufridasDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.penasDisciplinariasSufridas = penasDisciplinariasSufridas;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/penas-disciplinarias-sufridas', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/penas-disciplinarias-sufridas', { outlets: { popup: null } }]);
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
