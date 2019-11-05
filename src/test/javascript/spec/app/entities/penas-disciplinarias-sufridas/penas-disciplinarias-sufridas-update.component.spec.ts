import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { RrhhTestModule } from '../../../test.module';
import { PenasDisciplinariasSufridasUpdateComponent } from 'app/entities/penas-disciplinarias-sufridas/penas-disciplinarias-sufridas-update.component';
import { PenasDisciplinariasSufridasService } from 'app/entities/penas-disciplinarias-sufridas/penas-disciplinarias-sufridas.service';
import { PenasDisciplinariasSufridas } from 'app/shared/model/penas-disciplinarias-sufridas.model';

describe('Component Tests', () => {
  describe('PenasDisciplinariasSufridas Management Update Component', () => {
    let comp: PenasDisciplinariasSufridasUpdateComponent;
    let fixture: ComponentFixture<PenasDisciplinariasSufridasUpdateComponent>;
    let service: PenasDisciplinariasSufridasService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RrhhTestModule],
        declarations: [PenasDisciplinariasSufridasUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(PenasDisciplinariasSufridasUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PenasDisciplinariasSufridasUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PenasDisciplinariasSufridasService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PenasDisciplinariasSufridas(123);
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
        const entity = new PenasDisciplinariasSufridas();
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
