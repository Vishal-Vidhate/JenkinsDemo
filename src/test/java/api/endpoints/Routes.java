package api.endpoints;

/*
Swagger URI --> https://petstore.swagger.io/

Create User(Post) :    https://petstore.swagger.io/v2/user
GetUser (Get) :        https://petstore.swagger.io/v2/user/{username}
Update User (Put) :    https://petstore.swagger.io/v2/user/{username}
Delete User (Delete) : https://petstore.swagger.io/v2/user/{username}
*/

public class Routes {
	
	public static String base_url = "https://petstore.swagger.io/v2";
	
	//User Model (user is also common but this is part of endpoints not base URI)
	
	public static String post_url = base_url+"/user";
	public static String get_url = base_url+"/user/{username}";
	public static String update_url = base_url+"/user/{username}";
	public static String delete_url = base_url+"/user/{username}";
	
	//Store module
	
	  //Here we will create Store module URL's
	
	public static String store_post_order = base_url+"/user";
	public static String store_get_order = base_url+"/user/{orderId}";
	public static String store_delete_order = base_url+"/user/{orderId}";

	
	//Pet module
	
	  //Here we will create Pet module URL's
	  

}
