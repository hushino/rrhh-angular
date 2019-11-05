import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RrhhTestModule } from '../../../test.module';
import { AltasAscensosBajasDetailComponent } from 'app/entities/altas-ascensos-bajas/altas-ascensos-bajas-detail.component';
import { AltasAscensosBajas } from 'app/shared/model/altas-ascensos-bajas.model';

describe('Component Tests', () => {
  describe('AltasAscensosBajas Management Detail Component', () => {
    let comp: AltasAscensosBajasDetailComponent;
    let fixture: ComponentFixture<AltasAscensosBajasDetailComponent>;
    const route = ({ data: of({ altasAscensosBajas: new AltasAscensosBajas(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RrhhTestModule],
        declarations: [AltasAscensosBajasDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(AltasAscensosBajasDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AltasAscensosBajasDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.altasAscensosBajas).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
