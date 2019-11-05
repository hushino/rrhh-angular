import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPenasDisciplinariasSufridas } from 'app/shared/model/penas-disciplinarias-sufridas.model';

@Component({
  selector: 'jhi-penas-disciplinarias-sufridas-detail',
  templateUrl: './penas-disciplinarias-sufridas-detail.component.html'
})
export class PenasDisciplinariasSufridasDetailComponent implements OnInit {
  penasDisciplinariasSufridas: IPenasDisciplinariasSufridas;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ penasDisciplinariasSufridas }) => {
      this.penasDisciplinariasSufridas = penasDisciplinariasSufridas;
    });
  }

  previousState() {
    window.history.back();
  }
}
