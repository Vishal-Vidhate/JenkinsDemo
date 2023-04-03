package api.endpoints;

import java.io.IOException;

import api.payload.CreateUserPojo;
import api.utilities.PropertiesUtility;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateUser {
	
	public static Response testCreateUser(CreateUserPojo payload) throws IOException
	{
		RequestSpecification http = RestAssured.given();
		http.contentType(ContentType.JSON);
		http.body(payload);
		Response response = http.post(PropertiesUtility.getURL("post_reqres"));
		
		return response;
		
	}

}
