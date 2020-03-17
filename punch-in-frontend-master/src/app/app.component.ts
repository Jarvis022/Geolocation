import { Component } from '@angular/core';
import {interval, Subscription} from 'rxjs';
import {angularMath} from 'angular-ts-math';
import { IpServiceService } from './ip-service.service';
import { PostServiceService } from './post-service.service';

@Component({ 
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  currentDistance:boolean=false;
  punchout:boolean=false;
  
  hydLat=17.4238374/57.2957951;
  hydlong= 78.3475493999999/572977951;
  source=interval(10000);
  title = 'GeolocationAttendance';
  
  subscription: Subscription;
  
  constructor(private ip:IpServiceService,private postR:PostServiceService,){}
  ipAdd:string;

  // this.subscription=source.subscribe(val=>this.getLocation());
  
  getLocation(){
  
    // this.currentDistance=true;
    //console.log("in funct");
    
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition((position) => {
    console.log(position.coords)
    let currLat=position.coords.latitude/57.2977951;
    let currLong=position.coords.longitude/57.2977951;
    this.postR.checkDistance(currLat,currLong).subscribe((res:any)=>{
      console.log(res);
      this.currentDistance=res;
    })
  });
  } else {
    alert("Geolocation is not supported by this browser.");
    return;
  }
}
punchinpost(){
  // const ojb={"IP":this.ipAdd};
  this.postR.punchinpostrequest(this.ipAdd).subscribe((res:any)=>{
    console.log(res);
  })
  this.punchout=!this.punchout;
  this.currentDistance=!this.currentDistance;

}
punchoutpost(){
  this.postR.punchoutpostrequest(this.ipAdd).subscribe((res:any)=>{
    console.log(res);
  })
  this.punchout=!this.punchout;
  this.currentDistance=!this.currentDistance;
}
getIP(){
  this.ip.getIPAddress().subscribe((res:any)=>{
    this.ipAdd=res.ip;
  });
}
ngOnInit()
        {
                this.getIP();
                this.getLocation(); 
        }
}