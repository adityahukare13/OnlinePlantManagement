import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Coupan } from './coupan';

@Injectable({
  providedIn: 'root'
})
export class CoupanService {
  apiURL: any="http://localhost:8080";
  constructor(private httpClient:HttpClient) { }


  getAllCoupans():Observable<Coupan[]>{
    return this.httpClient.get<Coupan[]>(`${this.apiURL}/viewCoupan`);
  }


  deleteCoupanById (coupanId:number):Observable<string>{
    let httpheaders=new HttpHeaders()
    .set('Content-type','application/Json');
    let options={
      headers:httpheaders
    };
    return this.httpClient.delete(`${this.apiURL}/deleteCoupan/${coupanId}`, {responseType: 'text'});
  }
  addCoupan(coupan:Coupan,customerTd:number):Observable<Coupan>{
    let httpheaders=new HttpHeaders()
    .set('Content-type','application/Json');
    let options={
      headers:httpheaders
    };
    return this.httpClient.post<Coupan>(`${this.apiURL}/addCoupan/${customerTd}`,coupan,options);
  }
  


}

