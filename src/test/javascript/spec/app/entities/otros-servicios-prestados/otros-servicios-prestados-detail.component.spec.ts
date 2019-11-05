import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RrhhTestModule } from '../../../test.module';
import { OtrosServiciosPrestadosDetailComponent } from 'app/entities/otros-servicios-prestados/otros-servicios-prestados-detail.component';
import { OtrosServiciosPrestados } from 'app/shared/model/otros-servicios-prestados.model';

describe('Component Tests', () => {
  describe('OtrosServiciosPrestados Management Detail Component', () => {
    let comp: OtrosServiciosPrestadosDetailComponent;
    let fixture: ComponentFixture<OtrosServiciosPrestadosDetailComponent>;
    const route = ({ data: of({ otrosServiciosPrestados: new OtrosServiciosPrestados(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RrhhTestModule],
        declarations: [OtrosServiciosPrestadosDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(OtrosServiciosPrestadosDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(OtrosServiciosPrestadosDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.otrosServiciosPrestados).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
