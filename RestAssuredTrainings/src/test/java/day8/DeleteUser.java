package day8;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class DeleteUser {

	@Test
	void test_deleteUser(ITestContext context)
	{
		//int id = (int) context.getAttribute("user_id");
		int id = (int) context.getSuite().getAttribute("user_id");
		
		String bearerToken = "4ef702f86d03442f89dd7c2faf77492cc52484ce16edb3d67dbd69195f20ce3b";

		given()
		     .header("Authorization","Bearer "+bearerToken)
		     .pathParam("id", id)
		     
		.when()
		     .delete("https://gorest.co.in/public/v2/users/{id}")
		     
		.then()
		     .statusCode(204)
		     .log().all();
		
	}

}
