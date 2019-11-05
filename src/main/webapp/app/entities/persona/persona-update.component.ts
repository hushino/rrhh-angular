import { Component, OnInit, ElementRef } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IPersona, Persona } from 'app/shared/model/persona.model';
import { PersonaService } from './persona.service';

@Component({
  selector: 'jhi-persona-update',
  templateUrl: './persona-update.component.html'
})
export class PersonaUpdateComponent implements OnInit {
  isSaving: boolean;
  fechadeingresoDp: any;

  editForm = this.fb.group({
    id: [],
    nombre: [null, [Validators.required]],
    apellido: [null, [Validators.required]],
    cuil: [null, [Validators.required]],
    dni: [null, [Validators.required]],
    legajo: [null, [Validators.required]],
    apodo: [],
    foto: [],
    fotoContentType: [],
    soltero: [],
    casado: [],
    conviviente: [],
    viudo: [],
    domicilio: [],
    lugar: [],
    calle: [],
    numero: [],
    telefonofijo: [],
    numerodecelular: [],
    oficioprofecion: [],
    niveldeestudios: [],
    gruposanguineo: [],
    factor: [],
    donante: [],
    diabetes: [],
    hipertension: [],
    alergias: [],
    asma: [],
    otros: [],
    fechadeingreso: [],
    instrumentolegal: [],
    categoria: [],
    item: [],
    planta: [],
    area: [],
    direccion: [],
    annos: [],
    meses: [],
    dias: [],
    realizocomputodeservicios: [],
    poseeconocimientoenmaquinasviales: [],
    casoemergenciacelular: [],
    casoemergenciafijo: [],
    casoemergencianombre: [],
    casoemergenciacelular2: [],
    casoemergenciafijo2: [],
    casoemergencianombre2: [],
    familiaracargonombre: [],
    familiaracargonombre2: [],
    familiaracargonombre3: [],
    familiaracargonombre4: [],
    familiaracargonombre5: [],
    familiaracargodni: [],
    familiaracargodni2: [],
    familiaracargodni3: [],
    familiaracargodni4: [],
    familiaracargodni5: [],
    familiaracargoedad: [],
    familiaracargoedad2: [],
    familiaracargoedad3: [],
    familiaracargoedad4: [],
    familiaracargoedad5: [],
    altura: [],
    barrio: [],
    estudiosincompletos: [],
    conyugeapellido: [],
    conyugenombre: [],
    conyugedni: [],
    conyugecuil: [],
    grupofamiliarapellidonombre: [],
    grupofamiliarapellidonombre2: [],
    grupofamiliarapellidonombre3: [],
    grupofamiliarapellidonombre4: [],
    grupofamiliarapellidonombre5: [],
    grupofamiliarapellidonombre6: [],
    grupofamiliarapellidonombre7: [],
    grupofamiliarapellidonombre8: [],
    grupofamiliarapellidonombre9: [],
    grupofamiliarapellidonombre10: [],
    grupofamiliarapellidonombre11: [],
    grupofamiliarapellidonombreedad: [],
    grupofamiliarapellidonombreedad2: [],
    grupofamiliarapellidonombreedad3: [],
    grupofamiliarapellidonombreedad4: [],
    grupofamiliarapellidonombreedad5: [],
    grupofamiliarapellidonombreedad6: [],
    grupofamiliarapellidonombreedad7: [],
    grupofamiliarapellidonombreedad8: [],
    grupofamiliarapellidonombreedad9: [],
    grupofamiliarapellidonombreedad10: [],
    grupofamiliarapellidonombreedad11: [],
    grupofamiliarapellidonombredni: [],
    grupofamiliarapellidonombredni2: [],
    grupofamiliarapellidonombredni3: [],
    grupofamiliarapellidonombredni4: [],
    grupofamiliarapellidonombredni5: [],
    grupofamiliarapellidonombredni6: [],
    grupofamiliarapellidonombredni7: [],
    grupofamiliarapellidonombredni8: [],
    grupofamiliarapellidonombredni9: [],
    grupofamiliarapellidonombredni10: [],
    grupofamiliarapellidonombredni11: [],
    grupofamiliarapellidonombrefamiliar: [],
    grupofamiliarapellidonombrefamiliar2: [],
    grupofamiliarapellidonombrefamiliar4: [],
    grupofamiliarapellidonombrefamiliar3: [],
    grupofamiliarapellidonombrefamiliar5: [],
    grupofamiliarapellidonombrefamiliar6: [],
    grupofamiliarapellidonombrefamiliar7: [],
    grupofamiliarapellidonombrefamiliar8: [],
    grupofamiliarapellidonombrefamiliar9: [],
    grupofamiliarapellidonombrefamiliar10: [],
    grupofamiliarapellidonombrefamiliar11: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected personaService: PersonaService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ persona }) => {
      this.updateForm(persona);
    });
  }

  updateForm(persona: IPersona) {
    this.editForm.patchValue({
      id: persona.id,
      nombre: persona.nombre,
      apellido: persona.apellido,
      cuil: persona.cuil,
      dni: persona.dni,
      legajo: persona.legajo,
      apodo: persona.apodo,
      foto: persona.foto,
      fotoContentType: persona.fotoContentType,
      soltero: persona.soltero,
      casado: persona.casado,
      conviviente: persona.conviviente,
      viudo: persona.viudo,
      domicilio: persona.domicilio,
      lugar: persona.lugar,
      calle: persona.calle,
      numero: persona.numero,
      telefonofijo: persona.telefonofijo,
      numerodecelular: persona.numerodecelular,
      oficioprofecion: persona.oficioprofecion,
      niveldeestudios: persona.niveldeestudios,
      gruposanguineo: persona.gruposanguineo,
      factor: persona.factor,
      donante: persona.donante,
      diabetes: persona.diabetes,
      hipertension: persona.hipertension,
      alergias: persona.alergias,
      asma: persona.asma,
      otros: persona.otros,
      fechadeingreso: persona.fechadeingreso,
      instrumentolegal: persona.instrumentolegal,
      categoria: persona.categoria,
      item: persona.item,
      planta: persona.planta,
      area: persona.area,
      direccion: persona.direccion,
      annos: persona.annos,
      meses: persona.meses,
      dias: persona.dias,
      realizocomputodeservicios: persona.realizocomputodeservicios,
      poseeconocimientoenmaquinasviales: persona.poseeconocimientoenmaquinasviales,
      casoemergenciacelular: persona.casoemergenciacelular,
      casoemergenciafijo: persona.casoemergenciafijo,
      casoemergencianombre: persona.casoemergencianombre,
      casoemergenciacelular2: persona.casoemergenciacelular2,
      casoemergenciafijo2: persona.casoemergenciafijo2,
      casoemergencianombre2: persona.casoemergencianombre2,
      familiaracargonombre: persona.familiaracargonombre,
      familiaracargonombre2: persona.familiaracargonombre2,
      familiaracargonombre3: persona.familiaracargonombre3,
      familiaracargonombre4: persona.familiaracargonombre4,
      familiaracargonombre5: persona.familiaracargonombre5,
      familiaracargodni: persona.familiaracargodni,
      familiaracargodni2: persona.familiaracargodni2,
      familiaracargodni3: persona.familiaracargodni3,
      familiaracargodni4: persona.familiaracargodni4,
      familiaracargodni5: persona.familiaracargodni5,
      familiaracargoedad: persona.familiaracargoedad,
      familiaracargoedad2: persona.familiaracargoedad2,
      familiaracargoedad3: persona.familiaracargoedad3,
      familiaracargoedad4: persona.familiaracargoedad4,
      familiaracargoedad5: persona.familiaracargoedad5,
      altura: persona.altura,
      barrio: persona.barrio,
      estudiosincompletos: persona.estudiosincompletos,
      conyugeapellido: persona.conyugeapellido,
      conyugenombre: persona.conyugenombre,
      conyugedni: persona.conyugedni,
      conyugecuil: persona.conyugecuil,
      grupofamiliarapellidonombre: persona.grupofamiliarapellidonombre,
      grupofamiliarapellidonombre2: persona.grupofamiliarapellidonombre2,
      grupofamiliarapellidonombre3: persona.grupofamiliarapellidonombre3,
      grupofamiliarapellidonombre4: persona.grupofamiliarapellidonombre4,
      grupofamiliarapellidonombre5: persona.grupofamiliarapellidonombre5,
      grupofamiliarapellidonombre6: persona.grupofamiliarapellidonombre6,
      grupofamiliarapellidonombre7: persona.grupofamiliarapellidonombre7,
      grupofamiliarapellidonombre8: persona.grupofamiliarapellidonombre8,
      grupofamiliarapellidonombre9: persona.grupofamiliarapellidonombre9,
      grupofamiliarapellidonombre10: persona.grupofamiliarapellidonombre10,
      grupofamiliarapellidonombre11: persona.grupofamiliarapellidonombre11,
      grupofamiliarapellidonombreedad: persona.grupofamiliarapellidonombreedad,
      grupofamiliarapellidonombreedad2: persona.grupofamiliarapellidonombreedad2,
      grupofamiliarapellidonombreedad3: persona.grupofamiliarapellidonombreedad3,
      grupofamiliarapellidonombreedad4: persona.grupofamiliarapellidonombreedad4,
      grupofamiliarapellidonombreedad5: persona.grupofamiliarapellidonombreedad5,
      grupofamiliarapellidonombreedad6: persona.grupofamiliarapellidonombreedad6,
      grupofamiliarapellidonombreedad7: persona.grupofamiliarapellidonombreedad7,
      grupofamiliarapellidonombreedad8: persona.grupofamiliarapellidonombreedad8,
      grupofamiliarapellidonombreedad9: persona.grupofamiliarapellidonombreedad9,
      grupofamiliarapellidonombreedad10: persona.grupofamiliarapellidonombreedad10,
      grupofamiliarapellidonombreedad11: persona.grupofamiliarapellidonombreedad11,
      grupofamiliarapellidonombredni: persona.grupofamiliarapellidonombredni,
      grupofamiliarapellidonombredni2: persona.grupofamiliarapellidonombredni2,
      grupofamiliarapellidonombredni3: persona.grupofamiliarapellidonombredni3,
      grupofamiliarapellidonombredni4: persona.grupofamiliarapellidonombredni4,
      grupofamiliarapellidonombredni5: persona.grupofamiliarapellidonombredni5,
      grupofamiliarapellidonombredni6: persona.grupofamiliarapellidonombredni6,
      grupofamiliarapellidonombredni7: persona.grupofamiliarapellidonombredni7,
      grupofamiliarapellidonombredni8: persona.grupofamiliarapellidonombredni8,
      grupofamiliarapellidonombredni9: persona.grupofamiliarapellidonombredni9,
      grupofamiliarapellidonombredni10: persona.grupofamiliarapellidonombredni10,
      grupofamiliarapellidonombredni11: persona.grupofamiliarapellidonombredni11,
      grupofamiliarapellidonombrefamiliar: persona.grupofamiliarapellidonombrefamiliar,
      grupofamiliarapellidonombrefamiliar2: persona.grupofamiliarapellidonombrefamiliar2,
      grupofamiliarapellidonombrefamiliar4: persona.grupofamiliarapellidonombrefamiliar4,
      grupofamiliarapellidonombrefamiliar3: persona.grupofamiliarapellidonombrefamiliar3,
      grupofamiliarapellidonombrefamiliar5: persona.grupofamiliarapellidonombrefamiliar5,
      grupofamiliarapellidonombrefamiliar6: persona.grupofamiliarapellidonombrefamiliar6,
      grupofamiliarapellidonombrefamiliar7: persona.grupofamiliarapellidonombrefamiliar7,
      grupofamiliarapellidonombrefamiliar8: persona.grupofamiliarapellidonombrefamiliar8,
      grupofamiliarapellidonombrefamiliar9: persona.grupofamiliarapellidonombrefamiliar9,
      grupofamiliarapellidonombrefamiliar10: persona.grupofamiliarapellidonombrefamiliar10,
      grupofamiliarapellidonombrefamiliar11: persona.grupofamiliarapellidonombrefamiliar11
    });
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  setFileData(event, field: string, isImage) {
    return new Promise((resolve, reject) => {
      if (event && event.target && event.target.files && event.target.files[0]) {
        const file: File = event.target.files[0];
        if (isImage && !file.type.startsWith('image/')) {
          reject(`File was expected to be an image but was found to be ${file.type}`);
        } else {
          const filedContentType: string = field + 'ContentType';
          this.dataUtils.toBase64(file, base64Data => {
            this.editForm.patchValue({
              [field]: base64Data,
              [filedContentType]: file.type
            });
          });
        }
      } else {
        reject(`Base64 data was not set as file could not be extracted from passed parameter: ${event}`);
      }
    }).then(
      // eslint-disable-next-line no-console
      () => console.log('blob added'), // success
      this.onError
    );
  }

  clearInputImage(field: string, fieldContentType: string, idInput: string) {
    this.editForm.patchValue({
      [field]: null,
      [fieldContentType]: null
    });
    if (this.elementRef && idInput && this.elementRef.nativeElement.querySelector('#' + idInput)) {
      this.elementRef.nativeElement.querySelector('#' + idInput).value = null;
    }
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const persona = this.createFromForm();
    if (persona.id !== undefined) {
      this.subscribeToSaveResponse(this.personaService.update(persona));
    } else {
      this.subscribeToSaveResponse(this.personaService.create(persona));
    }
  }

  private createFromForm(): IPersona {
    return {
      ...new Persona(),
      id: this.editForm.get(['id']).value,
      nombre: this.editForm.get(['nombre']).value,
      apellido: this.editForm.get(['apellido']).value,
      cuil: this.editForm.get(['cuil']).value,
      dni: this.editForm.get(['dni']).value,
      legajo: this.editForm.get(['legajo']).value,
      apodo: this.editForm.get(['apodo']).value,
      fotoContentType: this.editForm.get(['fotoContentType']).value,
      foto: this.editForm.get(['foto']).value,
      soltero: this.editForm.get(['soltero']).value,
      casado: this.editForm.get(['casado']).value,
      conviviente: this.editForm.get(['conviviente']).value,
      viudo: this.editForm.get(['viudo']).value,
      domicilio: this.editForm.get(['domicilio']).value,
      lugar: this.editForm.get(['lugar']).value,
      calle: this.editForm.get(['calle']).value,
      numero: this.editForm.get(['numero']).value,
      telefonofijo: this.editForm.get(['telefonofijo']).value,
      numerodecelular: this.editForm.get(['numerodecelular']).value,
      oficioprofecion: this.editForm.get(['oficioprofecion']).value,
      niveldeestudios: this.editForm.get(['niveldeestudios']).value,
      gruposanguineo: this.editForm.get(['gruposanguineo']).value,
      factor: this.editForm.get(['factor']).value,
      donante: this.editForm.get(['donante']).value,
      diabetes: this.editForm.get(['diabetes']).value,
      hipertension: this.editForm.get(['hipertension']).value,
      alergias: this.editForm.get(['alergias']).value,
      asma: this.editForm.get(['asma']).value,
      otros: this.editForm.get(['otros']).value,
      fechadeingreso: this.editForm.get(['fechadeingreso']).value,
      instrumentolegal: this.editForm.get(['instrumentolegal']).value,
      categoria: this.editForm.get(['categoria']).value,
      item: this.editForm.get(['item']).value,
      planta: this.editForm.get(['planta']).value,
      area: this.editForm.get(['area']).value,
      direccion: this.editForm.get(['direccion']).value,
      annos: this.editForm.get(['annos']).value,
      meses: this.editForm.get(['meses']).value,
      dias: this.editForm.get(['dias']).value,
      realizocomputodeservicios: this.editForm.get(['realizocomputodeservicios']).value,
      poseeconocimientoenmaquinasviales: this.editForm.get(['poseeconocimientoenmaquinasviales']).value,
      casoemergenciacelular: this.editForm.get(['casoemergenciacelular']).value,
      casoemergenciafijo: this.editForm.get(['casoemergenciafijo']).value,
      casoemergencianombre: this.editForm.get(['casoemergencianombre']).value,
      casoemergenciacelular2: this.editForm.get(['casoemergenciacelular2']).value,
      casoemergenciafijo2: this.editForm.get(['casoemergenciafijo2']).value,
      casoemergencianombre2: this.editForm.get(['casoemergencianombre2']).value,
      familiaracargonombre: this.editForm.get(['familiaracargonombre']).value,
      familiaracargonombre2: this.editForm.get(['familiaracargonombre2']).value,
      familiaracargonombre3: this.editForm.get(['familiaracargonombre3']).value,
      familiaracargonombre4: this.editForm.get(['familiaracargonombre4']).value,
      familiaracargonombre5: this.editForm.get(['familiaracargonombre5']).value,
      familiaracargodni: this.editForm.get(['familiaracargodni']).value,
      familiaracargodni2: this.editForm.get(['familiaracargodni2']).value,
      familiaracargodni3: this.editForm.get(['familiaracargodni3']).value,
      familiaracargodni4: this.editForm.get(['familiaracargodni4']).value,
      familiaracargodni5: this.editForm.get(['familiaracargodni5']).value,
      familiaracargoedad: this.editForm.get(['familiaracargoedad']).value,
      familiaracargoedad2: this.editForm.get(['familiaracargoedad2']).value,
      familiaracargoedad3: this.editForm.get(['familiaracargoedad3']).value,
      familiaracargoedad4: this.editForm.get(['familiaracargoedad4']).value,
      familiaracargoedad5: this.editForm.get(['familiaracargoedad5']).value,
      altura: this.editForm.get(['altura']).value,
      barrio: this.editForm.get(['barrio']).value,
      estudiosincompletos: this.editForm.get(['estudiosincompletos']).value,
      conyugeapellido: this.editForm.get(['conyugeapellido']).value,
      conyugenombre: this.editForm.get(['conyugenombre']).value,
      conyugedni: this.editForm.get(['conyugedni']).value,
      conyugecuil: this.editForm.get(['conyugecuil']).value,
      grupofamiliarapellidonombre: this.editForm.get(['grupofamiliarapellidonombre']).value,
      grupofamiliarapellidonombre2: this.editForm.get(['grupofamiliarapellidonombre2']).value,
      grupofamiliarapellidonombre3: this.editForm.get(['grupofamiliarapellidonombre3']).value,
      grupofamiliarapellidonombre4: this.editForm.get(['grupofamiliarapellidonombre4']).value,
      grupofamiliarapellidonombre5: this.editForm.get(['grupofamiliarapellidonombre5']).value,
      grupofamiliarapellidonombre6: this.editForm.get(['grupofamiliarapellidonombre6']).value,
      grupofamiliarapellidonombre7: this.editForm.get(['grupofamiliarapellidonombre7']).value,
      grupofamiliarapellidonombre8: this.editForm.get(['grupofamiliarapellidonombre8']).value,
      grupofamiliarapellidonombre9: this.editForm.get(['grupofamiliarapellidonombre9']).value,
      grupofamiliarapellidonombre10: this.editForm.get(['grupofamiliarapellidonombre10']).value,
      grupofamiliarapellidonombre11: this.editForm.get(['grupofamiliarapellidonombre11']).value,
      grupofamiliarapellidonombreedad: this.editForm.get(['grupofamiliarapellidonombreedad']).value,
      grupofamiliarapellidonombreedad2: this.editForm.get(['grupofamiliarapellidonombreedad2']).value,
      grupofamiliarapellidonombreedad3: this.editForm.get(['grupofamiliarapellidonombreedad3']).value,
      grupofamiliarapellidonombreedad4: this.editForm.get(['grupofamiliarapellidonombreedad4']).value,
      grupofamiliarapellidonombreedad5: this.editForm.get(['grupofamiliarapellidonombreedad5']).value,
      grupofamiliarapellidonombreedad6: this.editForm.get(['grupofamiliarapellidonombreedad6']).value,
      grupofamiliarapellidonombreedad7: this.editForm.get(['grupofamiliarapellidonombreedad7']).value,
      grupofamiliarapellidonombreedad8: this.editForm.get(['grupofamiliarapellidonombreedad8']).value,
      grupofamiliarapellidonombreedad9: this.editForm.get(['grupofamiliarapellidonombreedad9']).value,
      grupofamiliarapellidonombreedad10: this.editForm.get(['grupofamiliarapellidonombreedad10']).value,
      grupofamiliarapellidonombreedad11: this.editForm.get(['grupofamiliarapellidonombreedad11']).value,
      grupofamiliarapellidonombredni: this.editForm.get(['grupofamiliarapellidonombredni']).value,
      grupofamiliarapellidonombredni2: this.editForm.get(['grupofamiliarapellidonombredni2']).value,
      grupofamiliarapellidonombredni3: this.editForm.get(['grupofamiliarapellidonombredni3']).value,
      grupofamiliarapellidonombredni4: this.editForm.get(['grupofamiliarapellidonombredni4']).value,
      grupofamiliarapellidonombredni5: this.editForm.get(['grupofamiliarapellidonombredni5']).value,
      grupofamiliarapellidonombredni6: this.editForm.get(['grupofamiliarapellidonombredni6']).value,
      grupofamiliarapellidonombredni7: this.editForm.get(['grupofamiliarapellidonombredni7']).value,
      grupofamiliarapellidonombredni8: this.editForm.get(['grupofamiliarapellidonombredni8']).value,
      grupofamiliarapellidonombredni9: this.editForm.get(['grupofamiliarapellidonombredni9']).value,
      grupofamiliarapellidonombredni10: this.editForm.get(['grupofamiliarapellidonombredni10']).value,
      grupofamiliarapellidonombredni11: this.editForm.get(['grupofamiliarapellidonombredni11']).value,
      grupofamiliarapellidonombrefamiliar: this.editForm.get(['grupofamiliarapellidonombrefamiliar']).value,
      grupofamiliarapellidonombrefamiliar2: this.editForm.get(['grupofamiliarapellidonombrefamiliar2']).value,
      grupofamiliarapellidonombrefamiliar4: this.editForm.get(['grupofamiliarapellidonombrefamiliar4']).value,
      grupofamiliarapellidonombrefamiliar3: this.editForm.get(['grupofamiliarapellidonombrefamiliar3']).value,
      grupofamiliarapellidonombrefamiliar5: this.editForm.get(['grupofamiliarapellidonombrefamiliar5']).value,
      grupofamiliarapellidonombrefamiliar6: this.editForm.get(['grupofamiliarapellidonombrefamiliar6']).value,
      grupofamiliarapellidonombrefamiliar7: this.editForm.get(['grupofamiliarapellidonombrefamiliar7']).value,
      grupofamiliarapellidonombrefamiliar8: this.editForm.get(['grupofamiliarapellidonombrefamiliar8']).value,
      grupofamiliarapellidonombrefamiliar9: this.editForm.get(['grupofamiliarapellidonombrefamiliar9']).value,
      grupofamiliarapellidonombrefamiliar10: this.editForm.get(['grupofamiliarapellidonombrefamiliar10']).value,
      grupofamiliarapellidonombrefamiliar11: this.editForm.get(['grupofamiliarapellidonombrefamiliar11']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPersona>>) {
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
}
