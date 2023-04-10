package day5;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ParsingXMLResponse {
	
	//@Test
	void testXMLResponse()
	{
		//Approach1
		
		given()
		
		.when()
		     .get("http://restapi.adequateshop.com/api/Traveler?page=1")
		
		.then()
		     .statusCode(200)
		     .header("Content-Type","application/xml; charset=utf-8")
		     .body("TravelerinformationResponse.page", equalTo("1"))
		     .body("TravelerinformationResponse.travelers.Travelerinformation[0].name", equalTo("Developer"));
		
		
		//Approach2

		Response res = given()
		
		               .when()
		                    .get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"),"application/xml; charset=utf-8");
		
		String page = res.xmlPath().getString("TravelerinformationResponse.page");
		Assert.assertEquals(page, "1");
		Assert.assertEquals(res.xmlPath().get("TravelerinformationResponse.page").toString(), "1");
		
		String name = res.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation[0].name");
		Assert.assertEquals(name, "Developer");
		
	}
	
	@Test
	void testXMLResponseBody()
	{
		//Approach2
		
		Response res = given()
		
		               .when()
		                    .get("http://restapi.adequateshop.com/api/Traveler?page=1");

		XmlPath xmlObj = new XmlPath(res.asString());
		
		List<String> travellers = xmlObj.getList("TravelerinformationResponse.travelers.Travelerinformation");
		
		int travellersNo = travellers.size();
		//System.out.println(travellersNo);
		Assert.assertEquals(travellersNo, 10);

		//Verify traveller name is present in response
		
		List<String> travellersNames = xmlObj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
				
		/*System.out.println(travellersNames);
		if(travellersNames.contains("Developer123"))
		{
			System.out.println("present");
		}*/
		
		boolean status=false;
		
		for(String travellersName:travellersNames)
		{
			if(travellersName.equals("Developer123"))
			{
				status=true;
				break;
			}
		}
		
		Assert.assertEquals(status, true);

	}
	
}
