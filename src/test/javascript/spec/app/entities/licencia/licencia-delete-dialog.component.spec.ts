import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { RrhhTestModule } from '../../../test.module';
import { LicenciaDeleteDialogComponent } from 'app/entities/licencia/licencia-delete-dialog.component';
import { LicenciaService } from 'app/entities/licencia/licencia.service';

describe('Component Tests', () => {
  describe('Licencia Management Delete Component', () => {
    let comp: LicenciaDeleteDialogComponent;
    let fixture: ComponentFixture<LicenciaDeleteDialogComponent>;
    let service: LicenciaService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RrhhTestModule],
        declarations: [LicenciaDeleteDialogComponent]
      })
        .overrideTemplate(LicenciaDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(LicenciaDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LicenciaService);
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
