package api.test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import api.endpoints.UserResponse;
import api.payload.UserPojo;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class UserDataDrivenTest {
	
	@DataProvider(name="Data")
    public String[][] getDataAll() throws IOException {
        return DataProviders.getAllData("UserData", "Sheet1");
    }
	
	//@Test(priority=1,dataProvider="Data",dataProviderClass=DataProviders.class)
	@Test(priority=1,dataProvider="Data",groups="Practice")
	public void testPostUser(String userID, String userName, String fname, String lname, String useremail, String pwd, String ph)
	{
		UserPojo userPayload = new UserPojo();
		
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstname(fname);
		userPayload.setLastname(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		Response response = UserResponse.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@DataProvider(name="userNames")
    public String[] getDataUserNames() throws IOException {
        return DataProviders.getUserNames("UserData", "Sheet1");
    }
	
	@Test(priority=2,dataProvider="userNames")
	public void testGetUserByName(String userName)
	{
		Response response = UserResponse.retriveUser(userName);	
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}	
	
	//@Test(priority=2,dataProvider="userNames",dataProviderClass=DataProviders.class)
	@Test(priority=3,dataProvider="userNames")
	public void testDeleteUserByName(String userName)
	{
		Response response = UserResponse.deleteUser(userName);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}	

}
