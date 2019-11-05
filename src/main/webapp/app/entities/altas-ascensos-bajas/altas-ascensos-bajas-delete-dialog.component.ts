import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAltasAscensosBajas } from 'app/shared/model/altas-ascensos-bajas.model';
import { AltasAscensosBajasService } from './altas-ascensos-bajas.service';

@Component({
  selector: 'jhi-altas-ascensos-bajas-delete-dialog',
  templateUrl: './altas-ascensos-bajas-delete-dialog.component.html'
})
export class AltasAscensosBajasDeleteDialogComponent {
  altasAscensosBajas: IAltasAscensosBajas;

  constructor(
    protected altasAscensosBajasService: AltasAscensosBajasService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.altasAscensosBajasService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'altasAscensosBajasListModification',
        content: 'Deleted an altasAscensosBajas'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-altas-ascensos-bajas-delete-popup',
  template: ''
})
export class AltasAscensosBajasDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ altasAscensosBajas }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(AltasAscensosBajasDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.altasAscensosBajas = altasAscensosBajas;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/altas-ascensos-bajas', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/altas-ascensos-bajas', { outlets: { popup: null } }]);
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
