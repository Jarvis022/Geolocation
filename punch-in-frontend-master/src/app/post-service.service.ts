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
    return this.http.post("http://localhost:8080/in",ip);
  }
  
  public punchoutpostrequest(ip):Observable<any>{
    return this.http.post("http://localhost:8080/out",ip);
  }
}
