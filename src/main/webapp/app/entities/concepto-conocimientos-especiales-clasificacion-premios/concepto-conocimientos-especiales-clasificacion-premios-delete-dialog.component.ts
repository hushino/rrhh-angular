import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IConceptoConocimientosEspecialesClasificacionPremios } from 'app/shared/model/concepto-conocimientos-especiales-clasificacion-premios.model';
import { ConceptoConocimientosEspecialesClasificacionPremiosService } from './concepto-conocimientos-especiales-clasificacion-premios.service';

@Component({
  selector: 'jhi-concepto-conocimientos-especiales-clasificacion-premios-delete-dialog',
  templateUrl: './concepto-conocimientos-especiales-clasificacion-premios-delete-dialog.component.html'
})
export class ConceptoConocimientosEspecialesClasificacionPremiosDeleteDialogComponent {
  conceptoConocimientosEspecialesClasificacionPremios: IConceptoConocimientosEspecialesClasificacionPremios;

  constructor(
    protected conceptoConocimientosEspecialesClasificacionPremiosService: ConceptoConocimientosEspecialesClasificacionPremiosService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.conceptoConocimientosEspecialesClasificacionPremiosService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'conceptoConocimientosEspecialesClasificacionPremiosListModification',
        content: 'Deleted an conceptoConocimientosEspecialesClasificacionPremios'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-concepto-conocimientos-especiales-clasificacion-premios-delete-popup',
  template: ''
})
export class ConceptoConocimientosEspecialesClasificacionPremiosDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ conceptoConocimientosEspecialesClasificacionPremios }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ConceptoConocimientosEspecialesClasificacionPremiosDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.conceptoConocimientosEspecialesClasificacionPremios = conceptoConocimientosEspecialesClasificacionPremios;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/concepto-conocimientos-especiales-clasificacion-premios', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/concepto-conocimientos-especiales-clasificacion-premios', { outlets: { popup: null } }]);
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
