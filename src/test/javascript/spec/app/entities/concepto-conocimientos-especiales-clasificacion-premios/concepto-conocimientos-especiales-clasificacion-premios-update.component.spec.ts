import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { RrhhTestModule } from '../../../test.module';
import { ConceptoConocimientosEspecialesClasificacionPremiosUpdateComponent } from 'app/entities/concepto-conocimientos-especiales-clasificacion-premios/concepto-conocimientos-especiales-clasificacion-premios-update.component';
import { ConceptoConocimientosEspecialesClasificacionPremiosService } from 'app/entities/concepto-conocimientos-especiales-clasificacion-premios/concepto-conocimientos-especiales-clasificacion-premios.service';
import { ConceptoConocimientosEspecialesClasificacionPremios } from 'app/shared/model/concepto-conocimientos-especiales-clasificacion-premios.model';

describe('Component Tests', () => {
  describe('ConceptoConocimientosEspecialesClasificacionPremios Management Update Component', () => {
    let comp: ConceptoConocimientosEspecialesClasificacionPremiosUpdateComponent;
    let fixture: ComponentFixture<ConceptoConocimientosEspecialesClasificacionPremiosUpdateComponent>;
    let service: ConceptoConocimientosEspecialesClasificacionPremiosService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RrhhTestModule],
        declarations: [ConceptoConocimientosEspecialesClasificacionPremiosUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ConceptoConocimientosEspecialesClasificacionPremiosUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ConceptoConocimientosEspecialesClasificacionPremiosUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ConceptoConocimientosEspecialesClasificacionPremiosService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ConceptoConocimientosEspecialesClasificacionPremios(123);
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
        const entity = new ConceptoConocimientosEspecialesClasificacionPremios();
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
