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
import {
  IConceptoConocimientosEspecialesClasificacionPremios,
  ConceptoConocimientosEspecialesClasificacionPremios
} from 'app/shared/model/concepto-conocimientos-especiales-clasificacion-premios.model';
import { ConceptoConocimientosEspecialesClasificacionPremiosService } from './concepto-conocimientos-especiales-clasificacion-premios.service';
import { IPersona } from 'app/shared/model/persona.model';
import { PersonaService } from 'app/entities/persona/persona.service';

@Component({
  selector: 'jhi-concepto-conocimientos-especiales-clasificacion-premios-update',
  templateUrl: './concepto-conocimientos-especiales-clasificacion-premios-update.component.html'
})
export class ConceptoConocimientosEspecialesClasificacionPremiosUpdateComponent implements OnInit {
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
    protected conceptoConocimientosEspecialesClasificacionPremiosService: ConceptoConocimientosEspecialesClasificacionPremiosService,
    protected personaService: PersonaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ conceptoConocimientosEspecialesClasificacionPremios }) => {
      this.updateForm(conceptoConocimientosEspecialesClasificacionPremios);
    });
    this.personaService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IPersona[]>) => mayBeOk.ok),
        map((response: HttpResponse<IPersona[]>) => response.body)
      )
      .subscribe((res: IPersona[]) => (this.personas = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(conceptoConocimientosEspecialesClasificacionPremios: IConceptoConocimientosEspecialesClasificacionPremios) {
    this.editForm.patchValue({
      id: conceptoConocimientosEspecialesClasificacionPremios.id,
      fecha: conceptoConocimientosEspecialesClasificacionPremios.fecha,
      referencias: conceptoConocimientosEspecialesClasificacionPremios.referencias,
      persona: conceptoConocimientosEspecialesClasificacionPremios.persona
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const conceptoConocimientosEspecialesClasificacionPremios = this.createFromForm();
    if (conceptoConocimientosEspecialesClasificacionPremios.id !== undefined) {
      this.subscribeToSaveResponse(
        this.conceptoConocimientosEspecialesClasificacionPremiosService.update(conceptoConocimientosEspecialesClasificacionPremios)
      );
    } else {
      this.subscribeToSaveResponse(
        this.conceptoConocimientosEspecialesClasificacionPremiosService.create(conceptoConocimientosEspecialesClasificacionPremios)
      );
    }
  }

  private createFromForm(): IConceptoConocimientosEspecialesClasificacionPremios {
    return {
      ...new ConceptoConocimientosEspecialesClasificacionPremios(),
      id: this.editForm.get(['id']).value,
      fecha: this.editForm.get(['fecha']).value,
      referencias: this.editForm.get(['referencias']).value,
      persona: this.editForm.get(['persona']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IConceptoConocimientosEspecialesClasificacionPremios>>) {
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
