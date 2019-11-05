import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { RrhhTestModule } from '../../../test.module';
import { EmbargosUpdateComponent } from 'app/entities/embargos/embargos-update.component';
import { EmbargosService } from 'app/entities/embargos/embargos.service';
import { Embargos } from 'app/shared/model/embargos.model';

describe('Component Tests', () => {
  describe('Embargos Management Update Component', () => {
    let comp: EmbargosUpdateComponent;
    let fixture: ComponentFixture<EmbargosUpdateComponent>;
    let service: EmbargosService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RrhhTestModule],
        declarations: [EmbargosUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(EmbargosUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EmbargosUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EmbargosService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Embargos(123);
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
        const entity = new Embargos();
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
