import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { LicenciaService } from 'app/entities/licencia/licencia.service';
import { ILicencia, Licencia } from 'app/shared/model/licencia.model';

describe('Service Tests', () => {
  describe('Licencia Service', () => {
    let injector: TestBed;
    let service: LicenciaService;
    let httpMock: HttpTestingController;
    let elemDefault: ILicencia;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(LicenciaService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Licencia(0, currentDate, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            fechaLicencia: currentDate.format(DATE_FORMAT)
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

      it('should create a Licencia', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            fechaLicencia: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            fechaLicencia: currentDate
          },
          returnedFromService
        );
        service
          .create(new Licencia(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Licencia', () => {
        const returnedFromService = Object.assign(
          {
            fechaLicencia: currentDate.format(DATE_FORMAT),
            referencias: 'BBBBBB',
            numeroDeDias: 'BBBBBB',
            observaciones: 'BBBBBB',
            usuariosMod: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fechaLicencia: currentDate
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

      it('should return a list of Licencia', () => {
        const returnedFromService = Object.assign(
          {
            fechaLicencia: currentDate.format(DATE_FORMAT),
            referencias: 'BBBBBB',
            numeroDeDias: 'BBBBBB',
            observaciones: 'BBBBBB',
            usuariosMod: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            fechaLicencia: currentDate
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

      it('should delete a Licencia', () => {
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
