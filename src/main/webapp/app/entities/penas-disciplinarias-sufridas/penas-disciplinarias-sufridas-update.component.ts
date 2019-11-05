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
import { IPenasDisciplinariasSufridas, PenasDisciplinariasSufridas } from 'app/shared/model/penas-disciplinarias-sufridas.model';
import { PenasDisciplinariasSufridasService } from './penas-disciplinarias-sufridas.service';
import { IPersona } from 'app/shared/model/persona.model';
import { PersonaService } from 'app/entities/persona/persona.service';

@Component({
  selector: 'jhi-penas-disciplinarias-sufridas-update',
  templateUrl: './penas-disciplinarias-sufridas-update.component.html'
})
export class PenasDisciplinariasSufridasUpdateComponent implements OnInit {
  isSaving: boolean;

  personas: IPersona[];
  fechaDp: any;

  editForm = this.fb.group({
    id: [],
    fecha: [],
    expediente: [],
    referencias: [],
    persona: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected penasDisciplinariasSufridasService: PenasDisciplinariasSufridasService,
    protected personaService: PersonaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ penasDisciplinariasSufridas }) => {
      this.updateForm(penasDisciplinariasSufridas);
    });
    this.personaService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IPersona[]>) => mayBeOk.ok),
        map((response: HttpResponse<IPersona[]>) => response.body)
      )
      .subscribe((res: IPersona[]) => (this.personas = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(penasDisciplinariasSufridas: IPenasDisciplinariasSufridas) {
    this.editForm.patchValue({
      id: penasDisciplinariasSufridas.id,
      fecha: penasDisciplinariasSufridas.fecha,
      expediente: penasDisciplinariasSufridas.expediente,
      referencias: penasDisciplinariasSufridas.referencias,
      persona: penasDisciplinariasSufridas.persona
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const penasDisciplinariasSufridas = this.createFromForm();
    if (penasDisciplinariasSufridas.id !== undefined) {
      this.subscribeToSaveResponse(this.penasDisciplinariasSufridasService.update(penasDisciplinariasSufridas));
    } else {
      this.subscribeToSaveResponse(this.penasDisciplinariasSufridasService.create(penasDisciplinariasSufridas));
    }
  }

  private createFromForm(): IPenasDisciplinariasSufridas {
    return {
      ...new PenasDisciplinariasSufridas(),
      id: this.editForm.get(['id']).value,
      fecha: this.editForm.get(['fecha']).value,
      expediente: this.editForm.get(['expediente']).value,
      referencias: this.editForm.get(['referencias']).value,
      persona: this.editForm.get(['persona']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPenasDisciplinariasSufridas>>) {
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
