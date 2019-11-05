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
import { IGarantia, Garantia } from 'app/shared/model/garantia.model';
import { GarantiaService } from './garantia.service';
import { IPersona } from 'app/shared/model/persona.model';
import { PersonaService } from 'app/entities/persona/persona.service';

@Component({
  selector: 'jhi-garantia-update',
  templateUrl: './garantia-update.component.html'
})
export class GarantiaUpdateComponent implements OnInit {
  isSaving: boolean;

  personas: IPersona[];
  presentadaFechaDp: any;

  editForm = this.fb.group({
    id: [],
    presentadaFecha: [],
    garantia: [],
    observaciones: [],
    persona: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected garantiaService: GarantiaService,
    protected personaService: PersonaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ garantia }) => {
      this.updateForm(garantia);
    });
    this.personaService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IPersona[]>) => mayBeOk.ok),
        map((response: HttpResponse<IPersona[]>) => response.body)
      )
      .subscribe((res: IPersona[]) => (this.personas = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(garantia: IGarantia) {
    this.editForm.patchValue({
      id: garantia.id,
      presentadaFecha: garantia.presentadaFecha,
      garantia: garantia.garantia,
      observaciones: garantia.observaciones,
      persona: garantia.persona
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const garantia = this.createFromForm();
    if (garantia.id !== undefined) {
      this.subscribeToSaveResponse(this.garantiaService.update(garantia));
    } else {
      this.subscribeToSaveResponse(this.garantiaService.create(garantia));
    }
  }

  private createFromForm(): IGarantia {
    return {
      ...new Garantia(),
      id: this.editForm.get(['id']).value,
      presentadaFecha: this.editForm.get(['presentadaFecha']).value,
      garantia: this.editForm.get(['garantia']).value,
      observaciones: this.editForm.get(['observaciones']).value,
      persona: this.editForm.get(['persona']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGarantia>>) {
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
