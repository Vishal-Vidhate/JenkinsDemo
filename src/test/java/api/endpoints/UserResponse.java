package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//UserEndpoints.java
//Created to perform CURD Operations (Create, Retrieve, Update, Delete requests to the user API)

public class UserResponse {
	
	public static Response createUser(UserPojo payload)
	{
		Response response = 
		given()
		     .contentType(ContentType.JSON)
		     .accept(ContentType.JSON)
		     .body(payload)
		
		.when()
		     .post(Routes.post_url);
		
		return response;
	}
	
	public static Response retriveUser(String userName)
	{
		Response response = 
		given()
		     .pathParam("username", userName)
		
		.when()
		     .get(Routes.get_url);
		
		return response;
	}
	
	public static Response updateUser(String userName,UserPojo payload)
	{
		Response response = 
		given()
		     .contentType(ContentType.JSON)
	         .accept(ContentType.JSON)
		     .pathParam("username", userName)
		     .body(payload)
		
		.when()
		     .put(Routes.update_url);
		
		return response;
	}
	
	public static Response deleteUser(String userName)
	{
		Response response = 
		given()
		     .pathParam("username", userName)
		
		.when()
		     .delete(Routes.delete_url);
		
		return response;
	}

}
