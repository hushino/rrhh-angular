import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RrhhTestModule } from '../../../test.module';
import { GarantiaDetailComponent } from 'app/entities/garantia/garantia-detail.component';
import { Garantia } from 'app/shared/model/garantia.model';

describe('Component Tests', () => {
  describe('Garantia Management Detail Component', () => {
    let comp: GarantiaDetailComponent;
    let fixture: ComponentFixture<GarantiaDetailComponent>;
    const route = ({ data: of({ garantia: new Garantia(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RrhhTestModule],
        declarations: [GarantiaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(GarantiaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GarantiaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.garantia).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
