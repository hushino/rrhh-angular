import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RrhhTestModule } from '../../../test.module';
import { PenasDisciplinariasSufridasDetailComponent } from 'app/entities/penas-disciplinarias-sufridas/penas-disciplinarias-sufridas-detail.component';
import { PenasDisciplinariasSufridas } from 'app/shared/model/penas-disciplinarias-sufridas.model';

describe('Component Tests', () => {
  describe('PenasDisciplinariasSufridas Management Detail Component', () => {
    let comp: PenasDisciplinariasSufridasDetailComponent;
    let fixture: ComponentFixture<PenasDisciplinariasSufridasDetailComponent>;
    const route = ({ data: of({ penasDisciplinariasSufridas: new PenasDisciplinariasSufridas(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RrhhTestModule],
        declarations: [PenasDisciplinariasSufridasDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(PenasDisciplinariasSufridasDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PenasDisciplinariasSufridasDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.penasDisciplinariasSufridas).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
