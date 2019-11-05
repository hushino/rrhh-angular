import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Data } from '@angular/router';

import { RrhhTestModule } from '../../../test.module';
import { ConceptoConocimientosEspecialesClasificacionPremiosComponent } from 'app/entities/concepto-conocimientos-especiales-clasificacion-premios/concepto-conocimientos-especiales-clasificacion-premios.component';
import { ConceptoConocimientosEspecialesClasificacionPremiosService } from 'app/entities/concepto-conocimientos-especiales-clasificacion-premios/concepto-conocimientos-especiales-clasificacion-premios.service';
import { ConceptoConocimientosEspecialesClasificacionPremios } from 'app/shared/model/concepto-conocimientos-especiales-clasificacion-premios.model';

describe('Component Tests', () => {
  describe('ConceptoConocimientosEspecialesClasificacionPremios Management Component', () => {
    let comp: ConceptoConocimientosEspecialesClasificacionPremiosComponent;
    let fixture: ComponentFixture<ConceptoConocimientosEspecialesClasificacionPremiosComponent>;
    let service: ConceptoConocimientosEspecialesClasificacionPremiosService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RrhhTestModule],
        declarations: [ConceptoConocimientosEspecialesClasificacionPremiosComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: {
              data: {
                subscribe: (fn: (value: Data) => void) =>
                  fn({
                    pagingParams: {
                      predicate: 'id',
                      reverse: false,
                      page: 0
                    }
                  })
              }
            }
          }
        ]
      })
        .overrideTemplate(ConceptoConocimientosEspecialesClasificacionPremiosComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ConceptoConocimientosEspecialesClasificacionPremiosComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ConceptoConocimientosEspecialesClasificacionPremiosService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ConceptoConocimientosEspecialesClasificacionPremios(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.conceptoConocimientosEspecialesClasificacionPremios[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ConceptoConocimientosEspecialesClasificacionPremios(123)],
            headers
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.conceptoConocimientosEspecialesClasificacionPremios[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should not load a page is the page is the same as the previous page', () => {
      spyOn(service, 'query').and.callThrough();

      // WHEN
      comp.loadPage(0);

      // THEN
      expect(service.query).toHaveBeenCalledTimes(0);
    });

    it('should re-initialize the page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ConceptoConocimientosEspecialesClasificacionPremios(123)],
            headers
          })
        )
      );

      // WHEN
      comp.loadPage(1);
      comp.clear();

      // THEN
      expect(comp.page).toEqual(0);
      expect(service.query).toHaveBeenCalledTimes(2);
      expect(comp.conceptoConocimientosEspecialesClasificacionPremios[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('should calculate the sort attribute for an id', () => {
      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // GIVEN
      comp.predicate = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
  });
});
