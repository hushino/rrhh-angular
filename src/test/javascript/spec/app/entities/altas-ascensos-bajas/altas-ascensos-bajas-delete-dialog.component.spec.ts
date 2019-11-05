import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { RrhhTestModule } from '../../../test.module';
import { AltasAscensosBajasDeleteDialogComponent } from 'app/entities/altas-ascensos-bajas/altas-ascensos-bajas-delete-dialog.component';
import { AltasAscensosBajasService } from 'app/entities/altas-ascensos-bajas/altas-ascensos-bajas.service';

describe('Component Tests', () => {
  describe('AltasAscensosBajas Management Delete Component', () => {
    let comp: AltasAscensosBajasDeleteDialogComponent;
    let fixture: ComponentFixture<AltasAscensosBajasDeleteDialogComponent>;
    let service: AltasAscensosBajasService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RrhhTestModule],
        declarations: [AltasAscensosBajasDeleteDialogComponent]
      })
        .overrideTemplate(AltasAscensosBajasDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AltasAscensosBajasDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AltasAscensosBajasService);
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
