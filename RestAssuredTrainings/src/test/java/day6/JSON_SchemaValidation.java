package day6;

import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

public class JSON_SchemaValidation {
	
	@Test
	void jsonSchemaValidation()
	{
		given()
		
		.when()
		     .get("http://localhost:3000/store")
		
		.then()
		     .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("JSONSchema.json"));
		
	}

}
