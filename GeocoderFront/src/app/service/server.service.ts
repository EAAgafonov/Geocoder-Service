import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { CustomResponse } from '../interface/custom-response';
import { DataJson } from '../interface/dt-json'


@Injectable({
  providedIn: 'root'
})
export class ServerService {
  private readonly apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  servers$ = <Observable<CustomResponse>>
    this.http.get<CustomResponse>(`${this.apiUrl}/data`)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );

  geocoderV2$ = (data: DataJson[]) => <Observable<CustomResponse>>
    this.http.post(`${this.apiUrl}/data/dt/final`, data)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      )

  sendCoordinates$ = (val1: string, val2: string) => <Observable<CustomResponse>>
    this.http.get<CustomResponse>(`${this.apiUrl}/data/dt/c/${val1}+${val2}`)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );

  sendAddress$ = (val1: string, val2: string, val3: string) => <Observable<CustomResponse>>
    this.http.get<CustomResponse>(`${this.apiUrl}/data/dt/a/${val1}+${val2}+${val3}`)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );


  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(() => error);
  }



}
