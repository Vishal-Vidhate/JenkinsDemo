package day5;

import java.io.File;

import org.apache.http.entity.ContentType;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class FileUploadAndDownload {
		
	@Test(priority=1)
	void singleFileUpload() 
	{
	     File myFile = new File("D:\\ST\\1SQL_Youtube.txt");

		given()
		     .multiPart("file",myFile)                 //This representing form data in postman
		     .contentType("multipart/form-data")
		
		.when()
		     .post("http://localhost:8080/uploadFile")
	
		.then()
		     .statusCode(200)
		     .body("fileName", equalTo("1SQL_Youtube.txt"))
		     .log().all();
	     
	}
	
	
	//@Test
	void multipleFilesUpload1() 
	{
	     File myFile1 = new File("D:\\ST\\1SQL_Youtube.txt");  // This part is compulsary
	     File myFile2 = new File("D:\\ST\\2_hrs_sql.txt");

		given()
		     .multiPart("files",myFile1)                 //This representing form data in postman
		     .multiPart("files",myFile2)
		     .contentType("multipart/form-data")
		
		.when()
		     .post("http://localhost:8080/uploadMultipleFiles")
	
		.then()
		     .statusCode(200)
		     .body("[0].fileName", equalTo("1SQL_Youtube.txt"))
		     .body("[1].fileName", equalTo("2_hrs_sql.txt"))
		     .log().all();
		
	}
		
		
		//@Test
		void multipleFilesUpload2()                //Won't work for all types of api , Depends upon how developer designed that api
		{
		     File myFile1 = new File("D:\\ST\\1SQL_Youtube.txt");      // This part is compulsary
		     File myFile2 = new File("D:\\ST\\2_hrs_sql.txt");
		     
		     File fileArr[] = {myFile1,myFile2};

			given()
			     .multiPart("files",fileArr)                 //This representing form data in postman
			     .contentType("multipart/form-data")
			
			.when()
			     .post("http://localhost:8080/uploadMultipleFiles")
		
			.then()
			     .statusCode(200)
			     .body("[0].fileName", equalTo("1SQL_Youtube.txt"))
			     .body("[1].fileName", equalTo("2_hrs_sql.txt"))
			     .log().all();
		}
		
		@Test(priority=2)
		void fileDownload()
		{
			given()
			
			.when()
			     .get("http://localhost:8080/downloadFile/1SQL_Youtube.txt")
			
			.then()
			     .log().all();
			 
		}

}
