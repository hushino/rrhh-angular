import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { RrhhTestModule } from '../../../test.module';
import { AltasAscensosBajasUpdateComponent } from 'app/entities/altas-ascensos-bajas/altas-ascensos-bajas-update.component';
import { AltasAscensosBajasService } from 'app/entities/altas-ascensos-bajas/altas-ascensos-bajas.service';
import { AltasAscensosBajas } from 'app/shared/model/altas-ascensos-bajas.model';

describe('Component Tests', () => {
  describe('AltasAscensosBajas Management Update Component', () => {
    let comp: AltasAscensosBajasUpdateComponent;
    let fixture: ComponentFixture<AltasAscensosBajasUpdateComponent>;
    let service: AltasAscensosBajasService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RrhhTestModule],
        declarations: [AltasAscensosBajasUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(AltasAscensosBajasUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AltasAscensosBajasUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AltasAscensosBajasService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new AltasAscensosBajas(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new AltasAscensosBajas();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
