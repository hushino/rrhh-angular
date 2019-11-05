import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { RrhhTestModule } from '../../../test.module';
import { ConceptoConocimientosEspecialesClasificacionPremiosDeleteDialogComponent } from 'app/entities/concepto-conocimientos-especiales-clasificacion-premios/concepto-conocimientos-especiales-clasificacion-premios-delete-dialog.component';
import { ConceptoConocimientosEspecialesClasificacionPremiosService } from 'app/entities/concepto-conocimientos-especiales-clasificacion-premios/concepto-conocimientos-especiales-clasificacion-premios.service';

describe('Component Tests', () => {
  describe('ConceptoConocimientosEspecialesClasificacionPremios Management Delete Component', () => {
    let comp: ConceptoConocimientosEspecialesClasificacionPremiosDeleteDialogComponent;
    let fixture: ComponentFixture<ConceptoConocimientosEspecialesClasificacionPremiosDeleteDialogComponent>;
    let service: ConceptoConocimientosEspecialesClasificacionPremiosService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RrhhTestModule],
        declarations: [ConceptoConocimientosEspecialesClasificacionPremiosDeleteDialogComponent]
      })
        .overrideTemplate(ConceptoConocimientosEspecialesClasificacionPremiosDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ConceptoConocimientosEspecialesClasificacionPremiosDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ConceptoConocimientosEspecialesClasificacionPremiosService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
