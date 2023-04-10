package day7;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class FakerDataGenerator {
	
	@Test
	void testGenerateDummyData()
	{
		Faker faker = new Faker();
		
		String fullName = faker.name().fullName();
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		
		String username = faker.name().username();
		
		String password = faker.internet().password();
		
		String phoneNo = faker.phoneNumber().cellPhone();
		
		String email = faker.internet().safeEmailAddress();
		
		String animal = faker.animal().name();
		

		
		System.out.println("fullName : "+fullName);
		System.out.println("firstName : "+firstName);
		System.out.println("lastName : "+lastName);
		System.out.println("username : "+username);
		System.out.println("password : "+password);
		System.out.println("phoneNo : "+phoneNo);
		System.out.println("email : "+email);
		System.out.println("animal : "+animal);
	}

}
