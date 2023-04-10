package day3;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeaderDemo {
	
	//@Test(priority=1)
	void testHeaders()
	{
		given()
		
		.when()
		     .get("https://www.google.com/")
		     
		.then()
		     .header("Content-Type", "text/html; charset=ISO-8859-1")
	         .and()  // Not necessary
		     .header("Content-Encoding", "gzip")
		     .header("Server", "gws");
	}
	
	@Test(priority=2)
	void testAllHeaders()
	{
		Response res = given()
		
		               .when()
		                    .get("https://www.google.com/");
		
		//Get single header info
		
		String headerValue = res.getHeader("Content-Type");
		System.out.println(headerValue);
		
		//Get all headers info

		Headers allHeaders = res.getHeaders();
		System.out.println(allHeaders);
		
		for(Header hd:allHeaders)
		{
			//System.out.println(hd.getName()+"    "+hd.getValue());
		}
		
		
	}
	
	

}
