import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { RrhhTestModule } from '../../../test.module';
import { GarantiaUpdateComponent } from 'app/entities/garantia/garantia-update.component';
import { GarantiaService } from 'app/entities/garantia/garantia.service';
import { Garantia } from 'app/shared/model/garantia.model';

describe('Component Tests', () => {
  describe('Garantia Management Update Component', () => {
    let comp: GarantiaUpdateComponent;
    let fixture: ComponentFixture<GarantiaUpdateComponent>;
    let service: GarantiaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RrhhTestModule],
        declarations: [GarantiaUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(GarantiaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GarantiaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GarantiaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Garantia(123);
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
        const entity = new Garantia();
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
