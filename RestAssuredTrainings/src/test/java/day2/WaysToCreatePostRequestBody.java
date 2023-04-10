package day2;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import Pojo_PostRequest.Location;
import Pojo_PostRequest.POJO;
import Pojo_PostRequest.Setter;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

/*
1)HashMap
2)using org.json
3)using POJO class (Plain Old Java Object)
4)using an external json file
5)As a string
*/

public class WaysToCreatePostRequestBody {
	
	//1)Post Request Body Using HashMap
	
	@Test(priority=1,enabled=false)
	void testPostUsingHashMap()
	{
		
		HashMap data = new HashMap();		
		
		data.put("name", "shubham");
		
		HashMap loc = new HashMap();
		loc.put("lat", 12345);
		loc.put("log", 678910);
		
		data.put("location", loc);
		
		data.put("phone", "9890534062");
		
		ArrayList course = new ArrayList();
		course.add("Developer");
		course.add("C#");
		data.put("courses", course);
		
		given()
		     .contentType("application/json")
		     .body(data)
		
		.when()
		     .post("http://localhost:3000/students")
		
		.then()
		     .statusCode(201)
		     .body("name", equalTo("shubham"))
		     .body("location", hasEntry("lat",12345))
		     .body("location", hasEntry("log",678910))
		     .body("phone", equalTo("9890534062"))
		     .body("courses[0]",equalTo("Developer"))
		     .body("courses[1]",equalTo("C#"))
		     .header("Content-Type",equalTo("application/json; charset=utf-8"))
		     .log().all();
	}
	
	
	
	//2)Post request body using org.json
	
	@Test(priority=2,enabled=false)
	
	void testPostUsingJsonLibrary()
	{
		
		JSONObject data = new JSONObject();
		
		data.put("name","sachin");
		
		JSONObject loc = new JSONObject();
		loc.put("lat", 11111);
		loc.put("log", 22222);
		
		data.put("location", loc);
		
		data.put("phone","1234569874");
		
		ArrayList course = new ArrayList();		
		course.add("maya");
		course.add("autocad");
		
		data.put("courses", course);
		
		given()
		     .contentType("application/json")
		     .body(data.toString())
		     
		.when()
		     .post("http://localhost:3000/students")
		     
		.then()
		     .statusCode(201)
		     .body("name",equalTo("sachin"))
		     .body("location",hasEntry("lat",11111))
		     .body("location",hasEntry("log",22222))
		     .body("phone", equalTo("1234569874"))
		     .body("courses[0]",equalTo("maya"))
		     .body("courses[1]",equalTo("autocad"))
		     .header("Content-Type", equalTo("application/json; charset=utf-8"));
		     

	}
	
	//3)Post request body using POJO class (Plain Old Java Object)
	
	@Test(priority=3,enabled=false)
	
	void testPostUsingPojo()
	{
		POJO pj = new POJO();
		
		pj.setName("rushi");
		
		Location loc = new Location();
		loc.setLat(12456);
		loc.setLog(88888);
		pj.setLocation(loc);
		
		pj.setPhone("9999999999");
		
		ArrayList list = new ArrayList();	
		list.add("ABM");
		list.add("Banking");		
		pj.setCourses(list);
		
		given()
		     .contentType("application/json")
		     .body(pj)
		     
		.when()
		     .post("http://localhost:3000/students")
		     
		.then()
		     .statusCode(201)
		     .body("name", equalTo(pj.getName()))
		     .body("location", hasEntry("lat",loc.getLat()))
		     .body("location", hasEntry("log",loc.getLog()))
		     .body("phone",equalTo(pj.getPhone()))
		     .body("courses",equalTo(pj.getCourses()))
		     .header("Content-Type", "application/json; charset=utf-8")
		     .log().all();
		     
	}
	
	//4)Post request body using external json file
	@Test(priority=4,enabled=true)
	
	void testPostUsingExternalJsonFile() throws FileNotFoundException
	{
		File f = new File(".\\body.json");
		
		FileReader fr = new FileReader(f);
		
		//We have to split that file in different tokens
		//Ultimately we have to get the json format of data
		//But the data we get is in the json format
		
		JSONTokener jt = new JSONTokener(fr);                 // org.json
		
		JSONObject data = new JSONObject(jt);
		
		given()
		     .contentType("application/json")
		     .body(data.toString())
		
		.when()
		     .post("http://localhost:3000/students")
		     
		 .then()
	     .statusCode(201)
	     .body("name",equalTo("vishal vidhate"))
	     .body("location",hasEntry("lat",67565443))
	     .body("location",hasEntry("log",52435555))
	     .body("phone", equalTo("1234567890"))
	     .body("courses[0]",equalTo("java2"))
	     .body("courses[1]",equalTo("selenium2"))
	     .header("Content-Type", equalTo("application/json; charset=utf-8"));		      
		          
	}
	
	
	
	//deletion of student record
	@Test(priority=5)
	void testDelete()
	{
		when()
		    .delete("http://localhost:3000/students/2")
		
		.then()
		     .statusCode(200);
		
	}
}
