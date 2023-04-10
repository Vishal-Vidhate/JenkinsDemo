package day8;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class CreateUserPost {
	
	@Test
	void test_createUser(ITestContext context)
	{
		Faker faker = new Faker();
		
		JSONObject data = new JSONObject();
		
		data.put("name", faker.name().firstName());
		data.put("gender", "Male");
		//faker.demographic().sex();
		data.put("email", faker.internet().emailAddress());
		data.put("status", "inactive");
		
		String bearerToken = "4ef702f86d03442f89dd7c2faf77492cc52484ce16edb3d67dbd69195f20ce3b";
		
		int id = 
				
		given()
		     .header("Authorization","Bearer "+bearerToken)
		     .contentType("application/json")
		     .body(data.toString())
		
		.when()
		     .post("https://gorest.co.in/public/v2/users")
		     .jsonPath().getInt("id");
		
		System.out.println("Generated id is : "+id);
		
		//In this after creating an id we generate a global variable or context variable by using :-
		//context.setAttribute("user_id", id);
		//So it will be available for other classes
		//This variable is created only at the Test level
		
		//If we have to make it available at the suite level then we need to change the statement
		
		context.getSuite().setAttribute("user_id", id);
		
	}

}
