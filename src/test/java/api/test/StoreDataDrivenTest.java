package api.test;

import static org.testng.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import api.endpoints.StoreResponse;
import api.payload.StorePojo;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class StoreDataDrivenTest {
	
	@DataProvider(name="Data")
	public String[][] getDataAll() throws IOException
	{
		return DataProviders.getAllData("StoreData", "Sheet1");
	}
	
	@Test(priority=1,dataProvider="Data")
	public void testPostOrder(String OrderId,String PetId,String Quantity,String ShipDate,String Status,String Complete) throws FileNotFoundException, JsonMappingException, JsonProcessingException
	{
		StorePojo storePayload = new StorePojo();
		
		storePayload.setId(Integer.parseInt(OrderId));
		storePayload.setPetId(Integer.parseInt(PetId));
		storePayload.setQuantity(Integer.parseInt(Quantity));
		storePayload.setShipDate(ShipDate);
		storePayload.setStatus(Status);
		storePayload.setComplete(Complete);
		
		Response response = StoreResponse.testOrderPlaces(storePayload);
		response.then().log().all();
		System.out.println(response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//ObjectMapper objectMapper = new ObjectMapper();
		
		//StorePojo pj = objectMapper.readValue(response.getBody().asString(), StorePojo.class);
		
		//System.out.println(pj.getId()+":"+pj.getPetId());

		/*Assert.assertEquals(pj.getId(), OrderId," OrderId Validated Successfully");
		Assert.assertEquals(pj.getPetId(), PetId," PetId Validated Successfully");
		Assert.assertEquals(pj.getQuantity(), Quantity," Quantity Validated Successfully");
		Assert.assertEquals(pj.getShipDate(), ShipDate," ShipDate Validated Successfully");
		Assert.assertEquals(pj.getStatus(), Status," Status Validated Successfully");
		Assert.assertEquals(pj.getComplete(), Complete," Complete Validated Successfully");*/
		
	}
	
	@DataProvider(name="orderId")
	public String[] getOrderId() throws IOException
	{
		return DataProviders.getUserNames("StoreData", "Sheet1");
	}
	
	@Test(priority=2,dataProvider="orderId")
	public void testGetOrder(String orderId)
	{
		Response response = StoreResponse.testGetOrderDetails(Integer.parseInt(orderId));
		response.then().log().all();
		int statusCode = response.getStatusCode();
		System.out.println(response.getStatusCode());
		assertTrue(statusCode == 400 || statusCode == 404 || statusCode == 200 , "Expected status code 400 or 403, but got " + statusCode);	
	}
	
	@Test(priority=3,dataProvider="orderId")
	public void testDeleteOrder(String orderId)
	{
		Response response = StoreResponse.testDeleteOrder(Integer.parseInt(orderId));
		response.then().log().all();
		int statusCode = response.getStatusCode();
		System.out.println(response.getStatusCode());
		assertTrue(statusCode == 400 || statusCode == 404, "Expected status code 400 or 403, but got " + statusCode);	
	}

}
