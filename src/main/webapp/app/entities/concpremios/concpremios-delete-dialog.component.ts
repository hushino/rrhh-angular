import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IConcpremios } from 'app/shared/model/concpremios.model';
import { ConcpremiosService } from './concpremios.service';

@Component({
  selector: 'jhi-concpremios-delete-dialog',
  templateUrl: './concpremios-delete-dialog.component.html'
})
export class ConcpremiosDeleteDialogComponent {
  concpremios: IConcpremios;

  constructor(
    protected concpremiosService: ConcpremiosService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.concpremiosService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'concpremiosListModification',
        content: 'Deleted an concpremios'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-concpremios-delete-popup',
  template: ''
})
export class ConcpremiosDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ concpremios }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ConcpremiosDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.concpremios = concpremios;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/concpremios', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/concpremios', { outlets: { popup: null } }]);
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
