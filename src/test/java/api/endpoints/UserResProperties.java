package api.endpoints;
	
import static io.restassured.RestAssured.given;

import java.io.IOException;

import api.payload.UserPojo;
import api.utilities.PropertiesUtility;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

	//UserEndpoints.java
	//Created to perform CURD Operations (Create, Retrieve, Update, Delete requests to the user API)

public class UserResProperties {
		
		public static Response createUser(UserPojo payload) throws IOException
		{
			Response response = 
			given()
			     .contentType(ContentType.JSON)
			     .accept(ContentType.JSON)
			     .body(payload)
			
			.when()
			     .post(PropertiesUtility.getURL("post_url"));
			
			return response;
		}
		
		public static Response retriveUser(String userName) throws IOException
		{
			Response response = 
			given()
			     .pathParam("username", userName)
			
			.when()
                 .get(PropertiesUtility.getURL("get_url"));	
			return response;
		}
		
		public static Response updateUser(String userName,UserPojo payload) throws IOException
		{
			Response response = 
			given()
			     .contentType(ContentType.JSON)
		         .accept(ContentType.JSON)
			     .pathParam("username", userName)
			     .body(payload)
			
			.when()
			     .put(PropertiesUtility.getURL("put_url"));
			
			return response;
		}
		
		public static Response deleteUser(String userName) throws IOException
		{
			Response response = 
			given()
			     .pathParam("username", userName)
			
			.when()
			     .delete(PropertiesUtility.getURL("delete_url"));
			
			return response;
		}

}
