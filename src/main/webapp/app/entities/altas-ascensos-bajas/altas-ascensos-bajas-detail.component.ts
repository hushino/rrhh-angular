import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAltasAscensosBajas } from 'app/shared/model/altas-ascensos-bajas.model';

@Component({
  selector: 'jhi-altas-ascensos-bajas-detail',
  templateUrl: './altas-ascensos-bajas-detail.component.html'
})
export class AltasAscensosBajasDetailComponent implements OnInit {
  altasAscensosBajas: IAltasAscensosBajas;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ altasAscensosBajas }) => {
      this.altasAscensosBajas = altasAscensosBajas;
    });
  }

  previousState() {
    window.history.back();
  }
}
