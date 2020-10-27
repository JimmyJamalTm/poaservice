import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({providedIn: 'root'})
export class PoaService {
  public API = '//localhost:8081';
  public POA_API = this.API + '/power-of-attorneys';

  constructor(private http: HttpClient) {
  }

  getAllPoas(): Observable<any> {
    return this.http.get(this.API );
  }

  get(id: string) {
    return this.http.get(this.POA_API + '/' + id);
  }

  save(poa: any): Observable<any> {
    let result: Observable<any>;
    if (poa.href) {
      result = this.http.put(poa.href, poa);
    } else {
      result = this.http.post(this.POA_API, poa);
    }
    return result;
  }

  remove(href: string) {
    return this.http.delete(href);
  }
}
