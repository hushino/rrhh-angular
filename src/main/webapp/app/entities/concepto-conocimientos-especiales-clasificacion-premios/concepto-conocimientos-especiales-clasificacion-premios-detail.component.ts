import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IConceptoConocimientosEspecialesClasificacionPremios } from 'app/shared/model/concepto-conocimientos-especiales-clasificacion-premios.model';

@Component({
  selector: 'jhi-concepto-conocimientos-especiales-clasificacion-premios-detail',
  templateUrl: './concepto-conocimientos-especiales-clasificacion-premios-detail.component.html'
})
export class ConceptoConocimientosEspecialesClasificacionPremiosDetailComponent implements OnInit {
  conceptoConocimientosEspecialesClasificacionPremios: IConceptoConocimientosEspecialesClasificacionPremios;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ conceptoConocimientosEspecialesClasificacionPremios }) => {
      this.conceptoConocimientosEspecialesClasificacionPremios = conceptoConocimientosEspecialesClasificacionPremios;
    });
  }

  previousState() {
    window.history.back();
  }
}
