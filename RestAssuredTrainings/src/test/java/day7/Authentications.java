package day7;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Authentications {
	
	//@Test(priority=1)
	
	void testBasicAuthentication()
	{
		given()
		     .auth().basic("postman", "password")  //This is api given by postman
		
		.when()
		     .get("https://postman-echo.com/basic-auth")
		
		.then()
		     .statusCode(200)
		     .body("authenticated", equalTo(true))
		     .log().all();	
	}
	
	//@Test(priority=2)
	
	void testDigestAuthentication()
	{
		given()
		     .auth().digest("postman", "password")  //This is api given by postman
		
		.when()
		     .get("https://postman-echo.com/basic-auth")
		
		.then()
		     .statusCode(200)
		     .body("authenticated", equalTo(true))
		     .log().all();	
	}

	
	//@Test(priority=3)
	
	void testPreemptiveAuthentication()
	{
		given()
		     .auth().preemptive().basic("postman", "password")  //This is api given by postman
		
		.when()
		     .get("https://postman-echo.com/basic-auth")
		
		.then()
		     .statusCode(200)
		     .body("authenticated", equalTo(true))
		     .log().all();	
	}
	
	
	//@Test(priority=4)
    void testBearerTokenAuthentication()
    {
		String bearerToken = "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";
		
		given()
		     .headers("Authorization","Bearer "+bearerToken)
		
		.when()
		     .get("https://api.github.com/user/repos")
		
		.then()
		     .statusCode(200)
		     .log().all();
    }
	
	
	//@Test(priority=5)
	void testOAuth1Authentication()
	{
		given()
		     .auth().oauth("consemerKey", "ConsumerSecrete", "accessToken", "tokenSecrete")    //This is for OAuth1.0 authentication 
		
		.when()
		     .get("url")
		     		
		.then()
		     .statusCode(200)
		     .log().all();
		
	}
	
	
	//@Test(priority=6)	
	void testOAuth2Authentication()
	{
		given()
	     .auth().oauth2("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")    //This is for OAuth2.0 authentication 
	
	.when()
	     .get("https://api.github.com/user/repos")
	     		
	.then()
	     .statusCode(200)
	     .log().all();
		
	}
	
	@Test(priority=7)
	void testAPIKey()
	{
		/*given()
		     //.queryParam("q", "Ahmednagar,MH,3166")
		     //.queryParam("limit", "3")
		     .queryParam("appid", "fccb99e68db02ba2a27687b7fc112108")
		
		.when()
		     .get("https://openweathermap.org/data/2.5/forcast/daily?q=Delhi&units=metric&cnt=7")
		     //.get("http://api.openweathermap.org/geo/1.0/direct?q=Ahmednagar,MH,3166&limit=3")
		     
		.then()
		     .statusCode(200)
		     .log().all();*/
		
		given()
	         .queryParam("appid", "fccb99e68db02ba2a27687b7fc112108")
	         .queryParam("q", "Delhi")
	         .queryParam("units", "metric")
	         .queryParam("cnt", "7")
	         
	         .pathParam("mypath", "data/2.5/forcast/daily")
	
	    .when()
	         //.get("https://openweathermap.org/data/2.5/forcast/daily?q=Delhi&units=metric&cnt=7")
	         .get("https://openweathermap.org/{mypath}")
	     
	    .then()
	         .statusCode(200)
	         .log().all();
		
	}

}
