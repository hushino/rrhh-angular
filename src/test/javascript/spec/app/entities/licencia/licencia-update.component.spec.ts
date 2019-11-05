import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { RrhhTestModule } from '../../../test.module';
import { LicenciaUpdateComponent } from 'app/entities/licencia/licencia-update.component';
import { LicenciaService } from 'app/entities/licencia/licencia.service';
import { Licencia } from 'app/shared/model/licencia.model';

describe('Component Tests', () => {
  describe('Licencia Management Update Component', () => {
    let comp: LicenciaUpdateComponent;
    let fixture: ComponentFixture<LicenciaUpdateComponent>;
    let service: LicenciaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RrhhTestModule],
        declarations: [LicenciaUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(LicenciaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LicenciaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LicenciaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Licencia(123);
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
        const entity = new Licencia();
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
