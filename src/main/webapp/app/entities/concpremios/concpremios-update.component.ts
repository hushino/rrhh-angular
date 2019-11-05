import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';
import { IConcpremios, Concpremios } from 'app/shared/model/concpremios.model';
import { ConcpremiosService } from './concpremios.service';
import { IPersona } from 'app/shared/model/persona.model';
import { PersonaService } from 'app/entities/persona/persona.service';

@Component({
  selector: 'jhi-concpremios-update',
  templateUrl: './concpremios-update.component.html'
})
export class ConcpremiosUpdateComponent implements OnInit {
  isSaving: boolean;

  personas: IPersona[];
  fechaDp: any;

  editForm = this.fb.group({
    id: [],
    fecha: [],
    referencias: [],
    persona: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected concpremiosService: ConcpremiosService,
    protected personaService: PersonaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ concpremios }) => {
      this.updateForm(concpremios);
    });
    this.personaService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IPersona[]>) => mayBeOk.ok),
        map((response: HttpResponse<IPersona[]>) => response.body)
      )
      .subscribe((res: IPersona[]) => (this.personas = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(concpremios: IConcpremios) {
    this.editForm.patchValue({
      id: concpremios.id,
      fecha: concpremios.fecha,
      referencias: concpremios.referencias,
      persona: concpremios.persona
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const concpremios = this.createFromForm();
    if (concpremios.id !== undefined) {
      this.subscribeToSaveResponse(this.concpremiosService.update(concpremios));
    } else {
      this.subscribeToSaveResponse(this.concpremiosService.create(concpremios));
    }
  }

  private createFromForm(): IConcpremios {
    return {
      ...new Concpremios(),
      id: this.editForm.get(['id']).value,
      fecha: this.editForm.get(['fecha']).value,
      referencias: this.editForm.get(['referencias']).value,
      persona: this.editForm.get(['persona']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IConcpremios>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackPersonaById(index: number, item: IPersona) {
    return item.id;
  }
}
