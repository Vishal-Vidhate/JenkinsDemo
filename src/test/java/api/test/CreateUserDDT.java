package api.test;

import java.io.IOException;
import java.time.Clock;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import api.endpoints.CreateUser;
import api.payload.CreateUserPojo;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class CreateUserDDT {
	
	@DataProvider(name="data")
	public String [][] getAllData() throws IOException
	{
		return DataProviders.getAllData("CreateUserData", "post_tc");
	}
	
	@Test(priority=1,dataProvider="data")
	public void testCreateUser(String name,String job) throws IOException
	{
		CreateUserPojo pj = new CreateUserPojo();
		pj.setName(name);
		pj.setJob(job);
		
		Response response = CreateUser.testCreateUser(pj);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 201);
		
		ObjectMapper objMapper = new ObjectMapper();
		
		CreateUserPojo pojo = objMapper.readValue(response.getBody().asString(), CreateUserPojo.class);

		System.out.println(pojo.getName());
		System.out.println(pojo.getJob());
		System.out.println(pojo.getId());
		System.out.println(pojo.getCreatedAt());
		
		Assert.assertEquals(pojo.getName(), name,"Name should be "+pojo.getName());
		Assert.assertEquals(pojo.getJob(), job,"Job should be "+pojo.getJob());
		Assert.assertNotNull(pojo.getId(),"Id is NULL");
		Assert.assertNotNull(pojo.getCreatedAt(),"CreatedAt is NULL");
		
		String createdAt = pojo.getCreatedAt().substring(0,10);
		System.out.println(createdAt);
	    String SysDate=java.time.LocalDate.now(Clock.systemUTC()).toString();
	    System.out.println(SysDate);
	    
		Assert.assertEquals(createdAt, SysDate);

	}

}
