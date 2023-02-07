import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Consumatore} from "../_interfaces/consumatore";

@Injectable({
  providedIn: 'root'
})
export class ConsumatoreService {

  constructor(private http: HttpClient) { }

  getAllConsumatori(): Observable<Consumatore[]>{
    return this.http.get<Consumatore[]>("http://localhost:8080/consumatore/getAllConsumatori");
  }


}
