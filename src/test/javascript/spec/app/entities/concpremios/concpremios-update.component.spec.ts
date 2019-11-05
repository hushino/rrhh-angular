import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { RrhhTestModule } from '../../../test.module';
import { ConcpremiosUpdateComponent } from 'app/entities/concpremios/concpremios-update.component';
import { ConcpremiosService } from 'app/entities/concpremios/concpremios.service';
import { Concpremios } from 'app/shared/model/concpremios.model';

describe('Component Tests', () => {
  describe('Concpremios Management Update Component', () => {
    let comp: ConcpremiosUpdateComponent;
    let fixture: ComponentFixture<ConcpremiosUpdateComponent>;
    let service: ConcpremiosService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RrhhTestModule],
        declarations: [ConcpremiosUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ConcpremiosUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ConcpremiosUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ConcpremiosService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Concpremios(123);
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
        const entity = new Concpremios();
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
