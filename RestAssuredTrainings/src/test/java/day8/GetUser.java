package day8;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class GetUser {
	
	@Test
	void test_getUser(ITestContext context) 
	{
		//int id = (int) context.getAttribute("user_id");   //This should come from Create User Post request
		                                                  //we need to use casting (wrapper class) because this get attribute method return some object type
		                                                  //so object type we cannot save directly into int variable
		
		int id = (int) context.getSuite().getAttribute("user_id");
		
		String bearerToken = "4ef702f86d03442f89dd7c2faf77492cc52484ce16edb3d67dbd69195f20ce3b";
		given()
		     .header("Authorization","Bearer "+bearerToken)
		     .pathParam("id", id)
		
		.when()
		     .get("https://gorest.co.in/public/v2/users/{id}")
		
		.then()
		     .statusCode(200)
		     .log().all();
	}

}
