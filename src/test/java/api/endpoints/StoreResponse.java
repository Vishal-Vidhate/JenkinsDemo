package api.endpoints;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import api.payload.StorePojo;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class StoreResponse {

	public static Response testOrderPlaces(StorePojo payload) throws FileNotFoundException
	{
		
		/*Response response = 
				given()
				     .contentType(ContentType.JSON)
				     .accept(ContentType.JSON)
				     .body(payload)
				
				.when()
				     .post(Routes.store_post_order);*/
		/*PrintStream log = new PrintStream(new FileOutputStream("log.txt"));

		RequestSpecification http = RestAssured.given();
		http.contentType(ContentType.JSON);
		http.accept(ContentType.JSON);
		http.body(payload);
		http.filter(RequestLoggingFilter.logRequestTo(log));
		http.filter(ResponseLoggingFilter.logResponseTo(log));
		Response response = http.post(Routes.store_post_order);*/
		
		
		PrintStream log = new PrintStream(new FileOutputStream("log2.txt"));
		RequestSpecification req = new RequestSpecBuilder()
		.setBaseUri("https://petstore.swagger.io/v2/user")
		.setContentType(ContentType.JSON)
		.setAccept(ContentType.JSON)
		//.addPathParam(key, value);
		//.addQueryParam(key, value);
		.addFilter(RequestLoggingFilter.logRequestTo(log))
		.addFilter(ResponseLoggingFilter.logResponseTo(log)).build();

		RequestSpecification reqs = given().spec(req).body(payload);
		
		Response response = reqs.when().post();
				
		return response;
	}
	
	public static Response testGetOrderDetails(int orderId)
	{
		Response response =
				given()
				     .pathParam("orderId", orderId)
	            
				.when()
				     .get(Routes.store_get_order);
		
		return response;
	}
	
	public static Response testDeleteOrder(int orderId)
	{
		Response response = 
				given()
			         .pathParam("orderId", orderId)
			
				.when()
				     .delete(Routes.store_delete_order);
		return response;
	}

}
