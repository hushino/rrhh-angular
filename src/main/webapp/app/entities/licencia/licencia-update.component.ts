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
import { ILicencia, Licencia } from 'app/shared/model/licencia.model';
import { LicenciaService } from './licencia.service';
import { IPersona } from 'app/shared/model/persona.model';
import { PersonaService } from 'app/entities/persona/persona.service';

@Component({
  selector: 'jhi-licencia-update',
  templateUrl: './licencia-update.component.html'
})
export class LicenciaUpdateComponent implements OnInit {
  isSaving: boolean;

  personas: IPersona[];
  fechaLicenciaDp: any;

  editForm = this.fb.group({
    id: [],
    fechaLicencia: [],
    referencias: [],
    numeroDeDias: [],
    observaciones: [],
    usuariosMod: [],
    persona: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected licenciaService: LicenciaService,
    protected personaService: PersonaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ licencia }) => {
      this.updateForm(licencia);
    });
    this.personaService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IPersona[]>) => mayBeOk.ok),
        map((response: HttpResponse<IPersona[]>) => response.body)
      )
      .subscribe((res: IPersona[]) => (this.personas = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(licencia: ILicencia) {
    this.editForm.patchValue({
      id: licencia.id,
      fechaLicencia: licencia.fechaLicencia,
      referencias: licencia.referencias,
      numeroDeDias: licencia.numeroDeDias,
      observaciones: licencia.observaciones,
      usuariosMod: licencia.usuariosMod,
      persona: licencia.persona
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const licencia = this.createFromForm();
    if (licencia.id !== undefined) {
      this.subscribeToSaveResponse(this.licenciaService.update(licencia));
    } else {
      this.subscribeToSaveResponse(this.licenciaService.create(licencia));
    }
  }

  private createFromForm(): ILicencia {
    return {
      ...new Licencia(),
      id: this.editForm.get(['id']).value,
      fechaLicencia: this.editForm.get(['fechaLicencia']).value,
      referencias: this.editForm.get(['referencias']).value,
      numeroDeDias: this.editForm.get(['numeroDeDias']).value,
      observaciones: this.editForm.get(['observaciones']).value,
      usuariosMod: this.editForm.get(['usuariosMod']).value,
      persona: this.editForm.get(['persona']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILicencia>>) {
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
