package api_endpoints;
/*
 swagger URI =https://petstore.swagger.io/
https://petstore.swagger.io/v2/user
 create usser(post): https://petstore.swagger.io/v2/user
 get usser(get): https://petstore.swagger.io/v2/user/{ussername}
 update usser(put): https://petstore.swagger.io/v2/user/{ussername}
 delete usser(delete): https://petstore.swagger.io/v2/user/{ussername}

 */
public class Routes {

	// usser module
	
	public static String base_Url="https://petstore.swagger.io/v2";

	public static String post_Url=base_Url+"/user";
	public static String get_Url=base_Url+"/user/{username}";
	public static String put_Url=base_Url+"/user/{username}";
	public static String delete_Url=base_Url+"/user/{username}";


}
