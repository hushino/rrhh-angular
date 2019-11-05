import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOtrosServiciosPrestados } from 'app/shared/model/otros-servicios-prestados.model';
import { OtrosServiciosPrestadosService } from './otros-servicios-prestados.service';

@Component({
  selector: 'jhi-otros-servicios-prestados-delete-dialog',
  templateUrl: './otros-servicios-prestados-delete-dialog.component.html'
})
export class OtrosServiciosPrestadosDeleteDialogComponent {
  otrosServiciosPrestados: IOtrosServiciosPrestados;

  constructor(
    protected otrosServiciosPrestadosService: OtrosServiciosPrestadosService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.otrosServiciosPrestadosService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'otrosServiciosPrestadosListModification',
        content: 'Deleted an otrosServiciosPrestados'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-otros-servicios-prestados-delete-popup',
  template: ''
})
export class OtrosServiciosPrestadosDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ otrosServiciosPrestados }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(OtrosServiciosPrestadosDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.otrosServiciosPrestados = otrosServiciosPrestados;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/otros-servicios-prestados', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/otros-servicios-prestados', { outlets: { popup: null } }]);
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
