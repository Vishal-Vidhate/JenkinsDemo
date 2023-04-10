package day6;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.matcher.RestAssuredMatchers;

public class XML_SchemaValidation {
	
	@Test
	void jsonSchemaValidation()
	{
		given()
		
		.when()
		     .get("http://restapi.adequateshop.com/api/Traveler?page=1")
		     
		.then()
		     .assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("XMLSchema.xsd"));
		
	}

}
