import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { PersonaService } from 'app/entities/persona/persona.service';
import { IPersona, Persona } from 'app/shared/model/persona.model';

describe('Service Tests', () => {
  describe('Persona Service', () => {
    let injector: TestBed;
    let service: PersonaService;
    let httpMock: HttpTestingController;
    let elemDefault: IPersona;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(PersonaService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Persona(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            fechadeingreso: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a Persona', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            fechadeingreso: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            fechadeingreso: currentDate
          },
          returnedFromService
        );
        service
          .create(new Persona(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Persona', () => {
        const returnedFromService = Object.assign(
          {
            nombre: 'BBBBBB',
            apellido: 'BBBBBB',
            cuil: 'BBBBBB',
            dni: 1,
            legajo: 1,
            apodo: 'BBBBBB',
            foto: 'BBBBBB',
            soltero: 'BBBBBB',
            casado: 'BBBBBB',
            conviviente: 'BBBBBB',
            viudo: 'BBBBBB',
            domicilio: 'BBBBBB',
            lugar: 'BBBBBB',
            calle: 'BBBBBB',
            numero: 'BBBBBB',
            telefonofijo: 'BBBBBB',
            numerodecelular: 'BBBBBB',
            oficioprofecion: 'BBBBBB',
            niveldeestudios: 'BBBBBB',
            gruposanguineo: 'BBBBBB',
            factor: 'BBBBBB',
            donante: 'BBBBBB',
            diabetes: 'BBBBBB',
            hipertension: 'BBBBBB',
            alergias: 'BBBBBB',
            asma: 'BBBBBB',
            otros: 'BBBBBB',
            fechadeingreso: currentDate.format(DATE_FORMAT),
            instrumentolegal: 'BBBBBB',
            categoria: 'BBBBBB',
            item: 'BBBBBB',
            planta: 'BBBBBB',
            area: 'BBBBBB',
            direccion: 'BBBBBB',
            annos: 1,
            meses: 1,
            dias: 1,
            realizocomputodeservicios: 'BBBBBB',
            poseeconocimientoenmaquinasviales: 'BBBBBB',
            casoemergenciacelular: 'BBBBBB',
            casoemergenciafijo: 'BBBBBB',
            casoemergencianombre: 'BBBBBB',
            casoemergenciacelular2: 'BBBBBB',
            casoemergenciafijo2: 'BBBBBB',
            casoemergencianombre2: 'BBBBBB',
            familiaracargonombre: 'BBBBBB',
            familiaracargonombre2: 'BBBBBB',
            familiaracargonombre3: 'BBBBBB',
            familiaracargonombre4: 'BBBBBB',
            familiaracargonombre5: 'BBBBBB',
            familiaracargodni: 'BBBBBB',
            familiaracargodni2: 'BBBBBB',
            familiaracargodni3: 'BBBBBB',
            familiaracargodni4: 'BBBBBB',
            familiaracargodni5: 'BBBBBB',
            familiaracargoedad: 'BBBBBB',
            familiaracargoedad2: 'BBBBBB',
            familiaracargoedad3: 'BBBBBB',
            familiaracargoedad4: 'BBBBBB',
            familiaracargoedad5: 'BBBBBB',
            altura: 'BBBBBB',
            barrio: 'BBBBBB',
            estudiosincompletos: 'BBBBBB',
            conyugeapellido: 'BBBBBB',
            conyugenombre: 'BBBBBB',
            conyugedni: 1,
            conyugecuil: 'BBBBBB',
            grupofamiliarapellidonombre: 'BBBBBB',
            grupofamiliarapellidonombre2: 'BBBBBB',
            grupofamiliarapellidonombre3: 'BBBBBB',
            grupofamiliarapellidonombre4: 'BBBBBB',
            grupofamiliarapellidonombre5: 'BBBBBB',
            grupofamiliarapellidonombre6: 'BBBBBB',
            grupofamiliarapellidonombre7: 'BBBBBB',
            grupofamiliarapellidonombre8: 'BBBBBB',
            grupofamiliarapellidonombre9: 'BBBBBB',
            grupofamiliarapellidonombre10: 'BBBBBB',
            grupofamiliarapellidonombre11: 'BBBBBB',
            grupofamiliarapellidonombreedad: 'BBBBBB',
            grupofamiliarapellidonombreedad2: 'BBBBBB',
            grupofamiliarapellidonombreedad3: 'BBBBBB',
            grupofamiliarapellidonombreedad4: 'BBBBBB',
            grupofamiliarapellidonombreedad5: 'BBBBBB',
            grupofamiliarapellidonombreedad6: 'BBBBBB',
            grupofamiliarapellidonombreedad7: 'BBBBBB',
            grupofamiliarapellidonombreedad8: 'BBBBBB',
            grupofamiliarapellidonombreedad9: 'BBBBBB',
            grupofamiliarapellidonombreedad10: 'BBBBBB',
            grupofamiliarapellidonombreedad11: 'BBBBBB',
            grupofamiliarapellidonombredni: 1,
            grupofamiliarapellidonombredni2: 1,
            grupofamiliarapellidonombredni3: 1,
            grupofamiliarapellidonombredni4: 1,
            grupofamiliarapellidonombredni5: 1,
            grupofamiliarapellidonombredni6: 1,
            grupofamiliarapellidonombredni7: 1,
            grupofamiliarapellidonombredni8: 1,
            grupofamiliarapellidonombredni9: 1,
            grupofamiliarapellidonombredni10: 1,
            grupofamiliarapellidonombredni11: 1,
            grupofamiliarapellidonombrefamiliar: 'BBBBBB',
            grupofamiliarapellidonombrefamiliar2: 'BBBBBB',
            grupofamiliarapellidonombrefamiliar4: 'BBBBBB',
            grupofamiliarapellidonombrefamiliar3: 'BBBBBB',
            grupofamiliarapellidonombrefamiliar5: 'BBBBBB',
            grupofamiliarapellidonombrefamiliar6: 'BBBBBB',
            grupofamiliarapellidonombrefamiliar7: 'BBBBBB',
            grupofamiliarapellidonombrefamiliar8: 'BBBBBB',
            grupofamiliarapellidonombrefamiliar9: 'BBBBBB',
            grupofamiliarapellidonombrefamiliar10: 'BBBBBB',
            grupofamiliarapellidonombrefamiliar11: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fechadeingreso: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of Persona', () => {
        const returnedFromService = Object.assign(
          {
            nombre: 'BBBBBB',
            apellido: 'BBBBBB',
            cuil: 'BBBBBB',
            dni: 1,
            legajo: 1,
            apodo: 'BBBBBB',
            foto: 'BBBBBB',
            soltero: 'BBBBBB',
            casado: 'BBBBBB',
            conviviente: 'BBBBBB',
            viudo: 'BBBBBB',
            domicilio: 'BBBBBB',
            lugar: 'BBBBBB',
            calle: 'BBBBBB',
            numero: 'BBBBBB',
            telefonofijo: 'BBBBBB',
            numerodecelular: 'BBBBBB',
            oficioprofecion: 'BBBBBB',
            niveldeestudios: 'BBBBBB',
            gruposanguineo: 'BBBBBB',
            factor: 'BBBBBB',
            donante: 'BBBBBB',
            diabetes: 'BBBBBB',
            hipertension: 'BBBBBB',
            alergias: 'BBBBBB',
            asma: 'BBBBBB',
            otros: 'BBBBBB',
            fechadeingreso: currentDate.format(DATE_FORMAT),
            instrumentolegal: 'BBBBBB',
            categoria: 'BBBBBB',
            item: 'BBBBBB',
            planta: 'BBBBBB',
            area: 'BBBBBB',
            direccion: 'BBBBBB',
            annos: 1,
            meses: 1,
            dias: 1,
            realizocomputodeservicios: 'BBBBBB',
            poseeconocimientoenmaquinasviales: 'BBBBBB',
            casoemergenciacelular: 'BBBBBB',
            casoemergenciafijo: 'BBBBBB',
            casoemergencianombre: 'BBBBBB',
            casoemergenciacelular2: 'BBBBBB',
            casoemergenciafijo2: 'BBBBBB',
            casoemergencianombre2: 'BBBBBB',
            familiaracargonombre: 'BBBBBB',
            familiaracargonombre2: 'BBBBBB',
            familiaracargonombre3: 'BBBBBB',
            familiaracargonombre4: 'BBBBBB',
            familiaracargonombre5: 'BBBBBB',
            familiaracargodni: 'BBBBBB',
            familiaracargodni2: 'BBBBBB',
            familiaracargodni3: 'BBBBBB',
            familiaracargodni4: 'BBBBBB',
            familiaracargodni5: 'BBBBBB',
            familiaracargoedad: 'BBBBBB',
            familiaracargoedad2: 'BBBBBB',
            familiaracargoedad3: 'BBBBBB',
            familiaracargoedad4: 'BBBBBB',
            familiaracargoedad5: 'BBBBBB',
            altura: 'BBBBBB',
            barrio: 'BBBBBB',
            estudiosincompletos: 'BBBBBB',
            conyugeapellido: 'BBBBBB',
            conyugenombre: 'BBBBBB',
            conyugedni: 1,
            conyugecuil: 'BBBBBB',
            grupofamiliarapellidonombre: 'BBBBBB',
            grupofamiliarapellidonombre2: 'BBBBBB',
            grupofamiliarapellidonombre3: 'BBBBBB',
            grupofamiliarapellidonombre4: 'BBBBBB',
            grupofamiliarapellidonombre5: 'BBBBBB',
            grupofamiliarapellidonombre6: 'BBBBBB',
            grupofamiliarapellidonombre7: 'BBBBBB',
            grupofamiliarapellidonombre8: 'BBBBBB',
            grupofamiliarapellidonombre9: 'BBBBBB',
            grupofamiliarapellidonombre10: 'BBBBBB',
            grupofamiliarapellidonombre11: 'BBBBBB',
            grupofamiliarapellidonombreedad: 'BBBBBB',
            grupofamiliarapellidonombreedad2: 'BBBBBB',
            grupofamiliarapellidonombreedad3: 'BBBBBB',
            grupofamiliarapellidonombreedad4: 'BBBBBB',
            grupofamiliarapellidonombreedad5: 'BBBBBB',
            grupofamiliarapellidonombreedad6: 'BBBBBB',
            grupofamiliarapellidonombreedad7: 'BBBBBB',
            grupofamiliarapellidonombreedad8: 'BBBBBB',
            grupofamiliarapellidonombreedad9: 'BBBBBB',
            grupofamiliarapellidonombreedad10: 'BBBBBB',
            grupofamiliarapellidonombreedad11: 'BBBBBB',
            grupofamiliarapellidonombredni: 1,
            grupofamiliarapellidonombredni2: 1,
            grupofamiliarapellidonombredni3: 1,
            grupofamiliarapellidonombredni4: 1,
            grupofamiliarapellidonombredni5: 1,
            grupofamiliarapellidonombredni6: 1,
            grupofamiliarapellidonombredni7: 1,
            grupofamiliarapellidonombredni8: 1,
            grupofamiliarapellidonombredni9: 1,
            grupofamiliarapellidonombredni10: 1,
            grupofamiliarapellidonombredni11: 1,
            grupofamiliarapellidonombrefamiliar: 'BBBBBB',
            grupofamiliarapellidonombrefamiliar2: 'BBBBBB',
            grupofamiliarapellidonombrefamiliar4: 'BBBBBB',
            grupofamiliarapellidonombrefamiliar3: 'BBBBBB',
            grupofamiliarapellidonombrefamiliar5: 'BBBBBB',
            grupofamiliarapellidonombrefamiliar6: 'BBBBBB',
            grupofamiliarapellidonombrefamiliar7: 'BBBBBB',
            grupofamiliarapellidonombrefamiliar8: 'BBBBBB',
            grupofamiliarapellidonombrefamiliar9: 'BBBBBB',
            grupofamiliarapellidonombrefamiliar10: 'BBBBBB',
            grupofamiliarapellidonombrefamiliar11: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            fechadeingreso: currentDate
          },
          returnedFromService
        );
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Persona', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
