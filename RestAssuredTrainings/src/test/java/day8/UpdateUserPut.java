package day8;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class UpdateUserPut {
	
	@Test
	void test_UpdateUser(ITestContext context)
	{
		Faker faker = new Faker();
		
		JSONObject data = new JSONObject();
		
		data.put("name", faker.name().firstName());
		data.put("gender", "Female");
		//faker.demographic().sex();
		data.put("email", faker.internet().emailAddress());
		data.put("status", "active");
		
		String bearerToken = "4ef702f86d03442f89dd7c2faf77492cc52484ce16edb3d67dbd69195f20ce3b";
		
		//int id = (int) context.getAttribute("user_id");
		int id = (int) context.getSuite().getAttribute("user_id");

		given()
		     .header("Authorization","Bearer "+bearerToken)
		     .contentType("application/json")
		     .pathParam("id", id)
		     .body(data.toString())
		
		.when()
		     .put("https://gorest.co.in/public/v2/users/{id}")
		     
		.then()
		     .statusCode(200)
		     .log().all();
		
		
	}
	

}
