package day4;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

public class ParsingJSONResponseData {

	// Approach1

	@Test(priority = 1)
	void testJsonResponse() {

		
		 given()
		      .contentType(ContentType.JSON)
		  
		 .when()
		      .get("http://localhost:3000/store")
		  
		 .then()
		      .statusCode(200) //.log().all()
		      .contentType("application/json; charset=utf-8") 
		      .body("book[3].title",equalTo("The Lord of the Rings"))
		      .log().all();
		 

	}

	// Approach2

	@Test(priority = 2)
	void testJsonResponseBodyData() {

		Response res = given().contentType(ContentType.JSON)

				.when()
				     .get("http://localhost:3000/store");

		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"), "application/json; charset=utf-8");

		String bookName = res.jsonPath().get("book[3].title").toString();
		// String bookName = res.jsonPath().getString("book[3].title");
		Assert.assertEquals(bookName, "The Lord of the Rings");
		
		//JSONObject class
		
		JSONObject jo = new JSONObject(res.asString());    //Converting response to json object type
		                                                   // Response --> String --> JSONObject
		
		for(int i=0;i<jo.getJSONArray("book").length();i++)
		{
			String bookTitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			System.out.println(bookTitle);
		}
		
		boolean status=false;
		
		for(int i=0;i<jo.getJSONArray("book").length();i++)
		{
			String bookTitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			if(bookTitle.equals("The Lord of the Rings"))
			{
				status=true;
				break;
			}
		}
		
		Assert.assertEquals(status, true);

	}
	
	@Test(priority = 3)
	void testJsonResponseBodyDataSum() {

		Response res = given().contentType(ContentType.JSON)

				.when()
				     .get("http://localhost:3000/store");
		
		JSONObject jo = new JSONObject(res.asString());    // Converting response to JSON object type
                                                           // Response --> String --> JSONObject
		
		double sum=0;
		
		for(int i=0;i<jo.getJSONArray("book").length();i++)
		{
			double sumPrice = jo.getJSONArray("book").getJSONObject(i).getDouble("price");
			System.out.println(sumPrice);
			sum=sum+sumPrice;
		}
		
		System.out.println(sum);
		Assert.assertEquals(sum, 526);
		
	}	

}
