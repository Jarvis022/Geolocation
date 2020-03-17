import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PostServiceService {

  constructor(private http:HttpClient) { 

  }
  public punchinpostrequest(ip):Observable<any>{
    return this.http.post("/in",ip);
  }
  
  public punchoutpostrequest(ip):Observable<any>{
    return this.http.post("/out",ip);
  }
  public checkDistance(lat,long):Observable<any>{
    let obj={
      "lat":lat,
      "long":long
    }
    return this.http.post("/check",obj);
  }
  
}
