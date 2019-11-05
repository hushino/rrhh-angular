import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RrhhTestModule } from '../../../test.module';
import { ConceptoConocimientosEspecialesClasificacionPremiosDetailComponent } from 'app/entities/concepto-conocimientos-especiales-clasificacion-premios/concepto-conocimientos-especiales-clasificacion-premios-detail.component';
import { ConceptoConocimientosEspecialesClasificacionPremios } from 'app/shared/model/concepto-conocimientos-especiales-clasificacion-premios.model';

describe('Component Tests', () => {
  describe('ConceptoConocimientosEspecialesClasificacionPremios Management Detail Component', () => {
    let comp: ConceptoConocimientosEspecialesClasificacionPremiosDetailComponent;
    let fixture: ComponentFixture<ConceptoConocimientosEspecialesClasificacionPremiosDetailComponent>;
    const route = ({
      data: of({ conceptoConocimientosEspecialesClasificacionPremios: new ConceptoConocimientosEspecialesClasificacionPremios(123) })
    } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RrhhTestModule],
        declarations: [ConceptoConocimientosEspecialesClasificacionPremiosDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ConceptoConocimientosEspecialesClasificacionPremiosDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ConceptoConocimientosEspecialesClasificacionPremiosDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.conceptoConocimientosEspecialesClasificacionPremios).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
