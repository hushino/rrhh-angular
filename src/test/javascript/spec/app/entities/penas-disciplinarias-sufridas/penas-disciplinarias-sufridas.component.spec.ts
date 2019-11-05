import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Data } from '@angular/router';

import { RrhhTestModule } from '../../../test.module';
import { PenasDisciplinariasSufridasComponent } from 'app/entities/penas-disciplinarias-sufridas/penas-disciplinarias-sufridas.component';
import { PenasDisciplinariasSufridasService } from 'app/entities/penas-disciplinarias-sufridas/penas-disciplinarias-sufridas.service';
import { PenasDisciplinariasSufridas } from 'app/shared/model/penas-disciplinarias-sufridas.model';

describe('Component Tests', () => {
  describe('PenasDisciplinariasSufridas Management Component', () => {
    let comp: PenasDisciplinariasSufridasComponent;
    let fixture: ComponentFixture<PenasDisciplinariasSufridasComponent>;
    let service: PenasDisciplinariasSufridasService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RrhhTestModule],
        declarations: [PenasDisciplinariasSufridasComponent],
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
        .overrideTemplate(PenasDisciplinariasSufridasComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PenasDisciplinariasSufridasComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PenasDisciplinariasSufridasService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new PenasDisciplinariasSufridas(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.penasDisciplinariasSufridas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new PenasDisciplinariasSufridas(123)],
            headers
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.penasDisciplinariasSufridas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
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
            body: [new PenasDisciplinariasSufridas(123)],
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
      expect(comp.penasDisciplinariasSufridas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
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
