package api_endpoints;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;
import api_payloads.User;
import io.restassured.response.Response;

public class UserEndPoints {

	public static Response createuser(User payload)
	{
		Response responce=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when()
				.post(Routes.post_Url);
		return responce;
	}

	public static Response readUser(String username)
	{
		Response responce=given()
				.pathParam("username", username)
				.when()
				.get(Routes.get_Url);
		return responce;
	}
	public static Response updateUser(String username, User payload)
	{
		Response responce=given()
				.pathParam("username", username)
			
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when()
				.put(Routes.put_Url);
		return responce;
	}
	public static Response deleteUser(String username)
	{
		Response responce=given()
				.pathParam("username", username)
				.when()
				.delete(Routes.delete_Url);
		return responce;
	}

}
