
import { Component, OnInit } from '@angular/core';
import { BehaviorSubject, catchError, map, Observable, of, startWith } from 'rxjs';
import { DataState } from './enum/data-state.enum';
import { AppState } from './interface/app-state';
import { DataJson } from './interface/dt-json'
import { CustomResponse } from './interface/custom-response';
import { ServerService } from './service/server.service';
import { HttpClient } from '@angular/common/http'

import data1 from './data/data1.json'
import data2 from './data/data2.json'
import data3 from './data/data3.json'


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  appState$: Observable<AppState<CustomResponse>>;
  readonly DataState = DataState;
  private dataSubject = new BehaviorSubject<CustomResponse>(null);

  dataJson1: DataJson[] = data1;
  dataJson2: DataJson[] = data2;
  dataJson3: DataJson[] = data3;

  public tstData: DataJson;

  constructor(private serverService: ServerService, private http: HttpClient) { }

  ngOnInit(): void {
    this.appState$ = this.serverService.servers$
      .pipe(
        map(response => {
          return { dataState: DataState.LOADED_STATE, appData: response }
        }),
        startWith({ dataState: DataState.LOADING_STATE }),
        catchError((error: string) => {
          return of({ dataState: DataState.ERROR_STATE, error })
        })
      );
    this.tstData = {}
  }

  geocoderV2(data: DataJson[]): void {
    this.appState$ = this.serverService.geocoderV2$(data)
      .pipe(
        map(response => {
          return { dataState: DataState.LOADED_STATE, appData: response }
        }),
        startWith({ dataState: DataState.LOADING_STATE, }),
        catchError((error: string) => {
          return of({ dataState: DataState.ERROR_STATE, error })
        })
      );
  }
  
  sendCoordinates(val1: string, val2: string): void {
    this.appState$ = this.serverService.sendCoordinates$(val1, val2)
      .pipe(
        map(response => {
          return { dataState: DataState.LOADED_STATE, appData: response }
        }),
        startWith({ dataState: DataState.LOADING_STATE, }),
        catchError((error: string) => {
          return of({ dataState: DataState.ERROR_STATE, error })
        })
      );
  }

  sendAddress(val1: string, val2: string, val3: string): void {
    this.appState$ = this.serverService.sendAddress$(val1, val2, val3)
      .pipe(
        map(response => {
          return { dataState: DataState.LOADED_STATE, appData: response }
        }),
        startWith({ dataState: DataState.LOADING_STATE }),
        catchError((error: string) => {
          return of({ dataState: DataState.ERROR_STATE, error })
        })
      )
  }







}

