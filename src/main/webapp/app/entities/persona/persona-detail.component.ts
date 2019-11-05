import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IPersona } from 'app/shared/model/persona.model';

@Component({
  selector: 'jhi-persona-detail',
  templateUrl: './persona-detail.component.html'
})
export class PersonaDetailComponent implements OnInit {
  persona: IPersona;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ persona }) => {
      this.persona = persona;
    });
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }
  previousState() {
    window.history.back();
  }
}
