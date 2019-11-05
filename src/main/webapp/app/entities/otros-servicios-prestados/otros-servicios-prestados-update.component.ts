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
import { IOtrosServiciosPrestados, OtrosServiciosPrestados } from 'app/shared/model/otros-servicios-prestados.model';
import { OtrosServiciosPrestadosService } from './otros-servicios-prestados.service';
import { IPersona } from 'app/shared/model/persona.model';
import { PersonaService } from 'app/entities/persona/persona.service';

@Component({
  selector: 'jhi-otros-servicios-prestados-update',
  templateUrl: './otros-servicios-prestados-update.component.html'
})
export class OtrosServiciosPrestadosUpdateComponent implements OnInit {
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
    protected otrosServiciosPrestadosService: OtrosServiciosPrestadosService,
    protected personaService: PersonaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ otrosServiciosPrestados }) => {
      this.updateForm(otrosServiciosPrestados);
    });
    this.personaService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IPersona[]>) => mayBeOk.ok),
        map((response: HttpResponse<IPersona[]>) => response.body)
      )
      .subscribe((res: IPersona[]) => (this.personas = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(otrosServiciosPrestados: IOtrosServiciosPrestados) {
    this.editForm.patchValue({
      id: otrosServiciosPrestados.id,
      fecha: otrosServiciosPrestados.fecha,
      referencias: otrosServiciosPrestados.referencias,
      persona: otrosServiciosPrestados.persona
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const otrosServiciosPrestados = this.createFromForm();
    if (otrosServiciosPrestados.id !== undefined) {
      this.subscribeToSaveResponse(this.otrosServiciosPrestadosService.update(otrosServiciosPrestados));
    } else {
      this.subscribeToSaveResponse(this.otrosServiciosPrestadosService.create(otrosServiciosPrestados));
    }
  }

  private createFromForm(): IOtrosServiciosPrestados {
    return {
      ...new OtrosServiciosPrestados(),
      id: this.editForm.get(['id']).value,
      fecha: this.editForm.get(['fecha']).value,
      referencias: this.editForm.get(['referencias']).value,
      persona: this.editForm.get(['persona']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOtrosServiciosPrestados>>) {
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
