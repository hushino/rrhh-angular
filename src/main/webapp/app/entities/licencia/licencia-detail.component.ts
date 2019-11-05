import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILicencia } from 'app/shared/model/licencia.model';

@Component({
  selector: 'jhi-licencia-detail',
  templateUrl: './licencia-detail.component.html'
})
export class LicenciaDetailComponent implements OnInit {
  licencia: ILicencia;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ licencia }) => {
      this.licencia = licencia;
    });
  }

  previousState() {
    window.history.back();
  }
}
