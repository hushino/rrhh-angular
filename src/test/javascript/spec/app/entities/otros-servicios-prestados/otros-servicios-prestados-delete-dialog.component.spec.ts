import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { RrhhTestModule } from '../../../test.module';
import { OtrosServiciosPrestadosDeleteDialogComponent } from 'app/entities/otros-servicios-prestados/otros-servicios-prestados-delete-dialog.component';
import { OtrosServiciosPrestadosService } from 'app/entities/otros-servicios-prestados/otros-servicios-prestados.service';

describe('Component Tests', () => {
  describe('OtrosServiciosPrestados Management Delete Component', () => {
    let comp: OtrosServiciosPrestadosDeleteDialogComponent;
    let fixture: ComponentFixture<OtrosServiciosPrestadosDeleteDialogComponent>;
    let service: OtrosServiciosPrestadosService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RrhhTestModule],
        declarations: [OtrosServiciosPrestadosDeleteDialogComponent]
      })
        .overrideTemplate(OtrosServiciosPrestadosDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OtrosServiciosPrestadosDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OtrosServiciosPrestadosService);
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
