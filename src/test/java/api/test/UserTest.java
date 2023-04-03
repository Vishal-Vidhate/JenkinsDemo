package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.endpoints.UserResponse;
import api.payload.UserPojo;
import io.restassured.response.Response;

public class UserTest {
	
	Faker faker;
	UserPojo userPayload;
	
	public Logger logger;   //for logs
	
	@BeforeClass
	public void setup()
	{
		faker = new Faker();
		userPayload=new UserPojo();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		
		logger = LogManager.getLogger(this.getClass());   //Same class name display in the log file
		                                                  //Now we can write the logs in the log files by using this logger object
		logger.debug("debugging.........");
	}
	
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("***************** Creating User ******************");  //Or we can write test case id
		
		Response response = UserResponse.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("***************** User is Created ******************");
	}
	
	@Test(priority=2)
	public void testGetUserByName()
	{
		logger.info("***************** Reading User Info ******************");
		//String userName = this.userPayload.getUsername();
		Response response = UserResponse.retriveUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("***************** User Info is Displayed ******************");
	}
	
	@Test(priority=3)
	public void testUpdateUserByName()
	{
		logger.info("***************** Updating User Info ******************");

		//update data using payload
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserResponse.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		response.then().log().body();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//Checking data after update
		Response responseAfterUpdate = UserResponse.updateUser(this.userPayload.getUsername(), userPayload);
		responseAfterUpdate.then().log().all();
		//responseAfterUpdate.then().log().body();
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		
		logger.info("***************** User is Updated ******************");
	}
	
	@Test(priority=4)
	public void testDeleteUserByName()
	{
		logger.info("***************** Deleting User ******************");

		Response response = UserResponse.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("***************** User Deleted ******************");
	}
	
	

}
