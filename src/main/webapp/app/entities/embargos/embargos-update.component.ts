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
import { IEmbargos, Embargos } from 'app/shared/model/embargos.model';
import { EmbargosService } from './embargos.service';
import { IPersona } from 'app/shared/model/persona.model';
import { PersonaService } from 'app/entities/persona/persona.service';

@Component({
  selector: 'jhi-embargos-update',
  templateUrl: './embargos-update.component.html'
})
export class EmbargosUpdateComponent implements OnInit {
  isSaving: boolean;

  personas: IPersona[];
  fechaDp: any;

  editForm = this.fb.group({
    id: [],
    fecha: [],
    juzgado: [],
    acreedor: [],
    cantidad: [],
    expediente: [],
    fianzaODeudaPropia: [],
    origenDeLaDeuda: [],
    observaciones: [],
    levantada: [],
    persona: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected embargosService: EmbargosService,
    protected personaService: PersonaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ embargos }) => {
      this.updateForm(embargos);
    });
    this.personaService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IPersona[]>) => mayBeOk.ok),
        map((response: HttpResponse<IPersona[]>) => response.body)
      )
      .subscribe((res: IPersona[]) => (this.personas = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(embargos: IEmbargos) {
    this.editForm.patchValue({
      id: embargos.id,
      fecha: embargos.fecha,
      juzgado: embargos.juzgado,
      acreedor: embargos.acreedor,
      cantidad: embargos.cantidad,
      expediente: embargos.expediente,
      fianzaODeudaPropia: embargos.fianzaODeudaPropia,
      origenDeLaDeuda: embargos.origenDeLaDeuda,
      observaciones: embargos.observaciones,
      levantada: embargos.levantada,
      persona: embargos.persona
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const embargos = this.createFromForm();
    if (embargos.id !== undefined) {
      this.subscribeToSaveResponse(this.embargosService.update(embargos));
    } else {
      this.subscribeToSaveResponse(this.embargosService.create(embargos));
    }
  }

  private createFromForm(): IEmbargos {
    return {
      ...new Embargos(),
      id: this.editForm.get(['id']).value,
      fecha: this.editForm.get(['fecha']).value,
      juzgado: this.editForm.get(['juzgado']).value,
      acreedor: this.editForm.get(['acreedor']).value,
      cantidad: this.editForm.get(['cantidad']).value,
      expediente: this.editForm.get(['expediente']).value,
      fianzaODeudaPropia: this.editForm.get(['fianzaODeudaPropia']).value,
      origenDeLaDeuda: this.editForm.get(['origenDeLaDeuda']).value,
      observaciones: this.editForm.get(['observaciones']).value,
      levantada: this.editForm.get(['levantada']).value,
      persona: this.editForm.get(['persona']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEmbargos>>) {
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
