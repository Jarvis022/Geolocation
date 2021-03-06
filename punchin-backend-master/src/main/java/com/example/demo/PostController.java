package com.example.demo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import Backend.UserModel;

@RestController
public class PostController {
	private final String INSERT_SQL = "INSERT INTO test1(IP,Timein) values(:IP,:Timein)";
    private final String UPDATE_SQL = "update test1 set Timeout = :time where IP=ip";
    private final String CHECK_SQL = "Select min(6371*acos(cos(radians(Latitude))*cos(radians(latitude))*cos(radians(longitude)-radians(Longitude))+sin(radians(Latitude))*sin(radians(latitude)))) from cities";
	
    
    
    
    @Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @GetMapping("/")
    public String store()
    {
        System.out.println("called");
        return "wokring";
    }

    @PostMapping("/in")
    public ResponseEntity<String> setStore(@RequestBody String IP )
    {
    	UserModel user=new UserModel();
    	user.setTimeStamp(System.currentTimeMillis( ));
    	user.setIP(IP);
    	KeyHolder holder = new GeneratedKeyHolder();
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("IP",user.getIP())
				.addValue("Timein",java.time.LocalTime.now());
		System.out.print("Time:  ");
		System.out.print(java.time.LocalTime.now());
		namedParameterJdbcTemplate.update(INSERT_SQL, parameters, holder);
		return new ResponseEntity<String>(HttpStatus.OK); 
    }
    @PostMapping("/out")
    public ResponseEntity<String> update(@RequestBody String IP)
    {
    	KeyHolder holder = new GeneratedKeyHolder();
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("ip",IP)
				.addValue("time",java.time.LocalTime.now());
		
    	namedParameterJdbcTemplate.update(UPDATE_SQL,parameters,holder);
		return new ResponseEntity<String>(HttpStatus.OK);
    }
    @PostMapping("/check")
    public ResponseEntity<Boolean> check(@RequestBody Map<String,Double> Jsn){
    	KeyHolder holder = new GeneratedKeyHolder();
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("longitude",Jsn.get("lat"))
				.addValue("longitude", Jsn.get("long"));
    	Double x=namedParameterJdbcTemplate.queryForObject(CHECK_SQL,parameters, Double.class);
    	Boolean ans;
    	if(x<=0.05) {
    		ans=true;
    	}
    	else {
    		ans=false;
    	}
    	return new ResponseEntity<Boolean>(ans,HttpStatus.OK);
    }
}