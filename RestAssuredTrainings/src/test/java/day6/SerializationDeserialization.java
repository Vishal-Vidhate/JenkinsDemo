package day6;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Pojo_PostRequest.Location;
import Pojo_PostRequest.POJO;

//Pojo --Serialize--> JSON Object --Deserialize--> Pojo

public class SerializationDeserialization {
	
	@Test(priority=0)
	
	void convertPojoToJson() throws JsonProcessingException
	{
		//Created Java Object using Pojo Class
		
		POJOClass pojo = new POJOClass();
		
		pojo.setName("rushi");
		
		LocationClass locn = new LocationClass();
		locn.setLat(12456);
		locn.setLog(88888);
		pojo.setLocation(locn);
		
		pojo.setPhone("9999999999");
		
		ArrayList list = new ArrayList();	
		list.add("ABM");
		list.add("Banking");
		
		pojo.setCourses(list);
		
		//Converting Java Object to Json Object  SERIALIZATION
		
		ObjectMapper objMapper =  new ObjectMapper();
		
		String jsonStringData = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(pojo);
		
		System.out.println(jsonStringData);
		
	}
	
	@Test(priority=1)
	
	void convertJsonToPojo() throws JsonProcessingException
	{

		String jsonData = "{\r\n"
				+ "  \"name\" : \"rushi\",\r\n"
				+ "  \"location\" : {\r\n"
				+ "    \"lat\" : 12456,\r\n"
				+ "    \"log\" : 88888\r\n"
				+ "  },\r\n"
				+ "  \"phone\" : \"9999999999\",\r\n"
				+ "  \"courses\" : [ \"ABM\", \"Banking\" ]\r\n"
				+ "}";
		
		//Converting Json Object to Java Object DESERIALIZATION
		
		ObjectMapper objMapper =  new ObjectMapper();
		
		POJOClass pj = objMapper.readValue(jsonData, POJOClass.class);   //Converts jsonData to POJOClass type object
		
		System.out.println(pj.getName());
		System.out.println(pj.getLocation().getLat());
		System.out.println(pj.getLocation().getLog());
		System.out.println(pj.getPhone());
		System.out.println(pj.getCourses().get(0));
		System.out.println(pj.getCourses().get(1));
		
	}

}
