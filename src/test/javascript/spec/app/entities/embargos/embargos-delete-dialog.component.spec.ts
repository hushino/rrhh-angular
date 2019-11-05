import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { RrhhTestModule } from '../../../test.module';
import { EmbargosDeleteDialogComponent } from 'app/entities/embargos/embargos-delete-dialog.component';
import { EmbargosService } from 'app/entities/embargos/embargos.service';

describe('Component Tests', () => {
  describe('Embargos Management Delete Component', () => {
    let comp: EmbargosDeleteDialogComponent;
    let fixture: ComponentFixture<EmbargosDeleteDialogComponent>;
    let service: EmbargosService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RrhhTestModule],
        declarations: [EmbargosDeleteDialogComponent]
      })
        .overrideTemplate(EmbargosDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EmbargosDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EmbargosService);
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
