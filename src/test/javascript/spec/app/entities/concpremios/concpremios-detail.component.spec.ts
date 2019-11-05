import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RrhhTestModule } from '../../../test.module';
import { ConcpremiosDetailComponent } from 'app/entities/concpremios/concpremios-detail.component';
import { Concpremios } from 'app/shared/model/concpremios.model';

describe('Component Tests', () => {
  describe('Concpremios Management Detail Component', () => {
    let comp: ConcpremiosDetailComponent;
    let fixture: ComponentFixture<ConcpremiosDetailComponent>;
    const route = ({ data: of({ concpremios: new Concpremios(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RrhhTestModule],
        declarations: [ConcpremiosDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ConcpremiosDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ConcpremiosDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.concpremios).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
