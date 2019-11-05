import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { RrhhTestModule } from '../../../test.module';
import { PenasDisciplinariasSufridasDeleteDialogComponent } from 'app/entities/penas-disciplinarias-sufridas/penas-disciplinarias-sufridas-delete-dialog.component';
import { PenasDisciplinariasSufridasService } from 'app/entities/penas-disciplinarias-sufridas/penas-disciplinarias-sufridas.service';

describe('Component Tests', () => {
  describe('PenasDisciplinariasSufridas Management Delete Component', () => {
    let comp: PenasDisciplinariasSufridasDeleteDialogComponent;
    let fixture: ComponentFixture<PenasDisciplinariasSufridasDeleteDialogComponent>;
    let service: PenasDisciplinariasSufridasService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RrhhTestModule],
        declarations: [PenasDisciplinariasSufridasDeleteDialogComponent]
      })
        .overrideTemplate(PenasDisciplinariasSufridasDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PenasDisciplinariasSufridasDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PenasDisciplinariasSufridasService);
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
