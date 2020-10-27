import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({providedIn: 'root'})
export class PoaService {
  public API = '//localhost:8088';
  public POA_API = this.API + '/power-of-attorneys';
  public DEBIT_CARD_API = this.API + '/debit-cards/'
  public CREDIT_CARD_API = this.API + '/credit-cards/'
  public ACCOUNT_API = this.API + '/accounts/'

  constructor(private http: HttpClient) {
  }

  getAllPoas(): Observable<any> {
    return this.http.get(this.POA_API);
  }

  getPoa(id: string) : any {
    return this.http.get(this.POA_API + '/' + id);
  }

  getDebitcard(id: string): any  {
    return this.http.get(this.DEBIT_CARD_API + id);
  }

  getCreditcard(id: string): any  {
    return this.http.get(this.CREDIT_CARD_API + id);
  }

  getAccount(id: string): any {
    return this.http.get(this.ACCOUNT_API + id.substring(8));
  }

}
