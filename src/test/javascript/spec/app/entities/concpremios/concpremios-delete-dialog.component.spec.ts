import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { RrhhTestModule } from '../../../test.module';
import { ConcpremiosDeleteDialogComponent } from 'app/entities/concpremios/concpremios-delete-dialog.component';
import { ConcpremiosService } from 'app/entities/concpremios/concpremios.service';

describe('Component Tests', () => {
  describe('Concpremios Management Delete Component', () => {
    let comp: ConcpremiosDeleteDialogComponent;
    let fixture: ComponentFixture<ConcpremiosDeleteDialogComponent>;
    let service: ConcpremiosService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RrhhTestModule],
        declarations: [ConcpremiosDeleteDialogComponent]
      })
        .overrideTemplate(ConcpremiosDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ConcpremiosDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ConcpremiosService);
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
