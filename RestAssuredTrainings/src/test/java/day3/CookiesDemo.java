package day3;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class CookiesDemo {
	
	@Test(priority=1,enabled=false)
	void testCookies()
	{
		given()
		
		.when()
		     .get("https://www.google.com/")
		
		.then()
		     .cookie("AEC","ARSKqsJgTEc86qSxGVAEDEQLMhBvzXChbYje7B3sd13JDVfFPwubjIFk55U")
		     .log().all();
	}
	
	@Test(priority=2)
	void getCookiesInfo()
	{
		Response res=when()
		                 .get("https://www.google.com/");
				                  
		//get single cookie info
		//String cookie_value=res.getCookie("AEC");
		//System.out.println("Value of cookie is ======>"+cookie_value);
		
		
		//get all cookies info
		Map<String,String> cookies_values=res.getCookies();    //return type is map in key and value pair
		
		for(Map.Entry entry:cookies_values.entrySet())
		{
			System.out.println(entry.getKey()+"      "+entry.getValue());
			Assert.assertNotNull(entry.getValue());
		}
		
		/*System.out.println(cookies_values.keySet());
		
		for(String key:cookies_values.keySet())
		{
			System.out.println(key+"       "+cookies_values.get(key));
		}
		
		for(String key:cookies_values.keySet())
		{
			String cookie_value=res.getCookie(key);
			System.out.println(key+"       "+cookie_value);
		}*/
				   
	}

}
