import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Plant } from './plant';

@Injectable({
  providedIn: 'root'
})
export class PlantService {
  apiURL: any="http://localhost:8080";
  constructor(private httpClient:HttpClient) { }


  getAllPlants():Observable<Plant[]>{
    return this.httpClient.get<Plant[]>(`${this.apiURL}/viewPlant`);
  }

//   getAllPlants():Observable<Plant[]>{
    
// const ArrayProperty:Array<Plant>=[];
// // return this.httpClient.get<Iplant[]>(`${this.apiURL}/viewPlant`);
// console.log(`${this.apiURL}/viewPlant`);
//     return  this.httpClient.get<Plant[]>(`${this.apiURL}/viewPlant`).pipe(
// map(data=>{
//   for(const id in data){
//     ArrayProperty.push(data[id])
//   }
//   return ArrayProperty;

// })
//     );

//   }
  deletePlantById (plantId:number,customerTd:number):Observable<string>{
    let httpheaders=new HttpHeaders()
    .set('Content-type','application/Json');
    let options={
      headers:httpheaders
    };
    return this.httpClient.delete(`${this.apiURL}/deletePlant/${plantId}/${customerTd}`, {responseType: 'text'});
  }
  addPlant(plant:Plant,customerTd:number):Observable<Plant>{
    let httpheaders=new HttpHeaders()
    .set('Content-type','application/Json');
    let options={
      headers:httpheaders
    };
    return this.httpClient.post<Plant>(`${this.apiURL}/addPlant/${customerTd}`,plant,options);
  }
  updatePlant(plant:Plant,customerTd:number):Observable<Plant>{
    let httpheaders=new HttpHeaders()
    .set('Content-type','application/Json');
    let options={
      headers:httpheaders
    };
    return this.httpClient.put<Plant>(`${this.apiURL}/updatePlant/${customerTd}`,plant,options);
  }


}

