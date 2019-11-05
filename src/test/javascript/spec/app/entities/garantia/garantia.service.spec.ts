import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { GarantiaService } from 'app/entities/garantia/garantia.service';
import { IGarantia, Garantia } from 'app/shared/model/garantia.model';

describe('Service Tests', () => {
  describe('Garantia Service', () => {
    let injector: TestBed;
    let service: GarantiaService;
    let httpMock: HttpTestingController;
    let elemDefault: IGarantia;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(GarantiaService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Garantia(0, currentDate, 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            presentadaFecha: currentDate.format(DATE_FORMAT)
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

      it('should create a Garantia', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            presentadaFecha: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            presentadaFecha: currentDate
          },
          returnedFromService
        );
        service
          .create(new Garantia(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Garantia', () => {
        const returnedFromService = Object.assign(
          {
            presentadaFecha: currentDate.format(DATE_FORMAT),
            garantia: 'BBBBBB',
            observaciones: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            presentadaFecha: currentDate
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

      it('should return a list of Garantia', () => {
        const returnedFromService = Object.assign(
          {
            presentadaFecha: currentDate.format(DATE_FORMAT),
            garantia: 'BBBBBB',
            observaciones: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            presentadaFecha: currentDate
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

      it('should delete a Garantia', () => {
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
