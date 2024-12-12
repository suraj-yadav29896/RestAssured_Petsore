package api_endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api_payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoint2Proprty {
	// method creating for getting url in properties file this getbundle method by defoult call prpeties file no need to set locationmof properties file

	static ResourceBundle getUrl()
	{
		ResourceBundle routes=ResourceBundle.getBundle("routes");// load propeties file
		return routes;
	}

	public static Response createuser(User payload)

	{
		String post_Url=getUrl().getString("post_url");
		Response responce=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when()
				.post(post_Url);
		return responce;
	}

	public static Response readUser(String username)

	{
		String get_Url=getUrl().getString("get_url");
		Response responce=given()
				.pathParam("username", username)
				.when()
				.get(get_Url);
		return responce;
	}
	public static Response updateUser(String username, User payload)
	{
		String put_url=getUrl().getString("put_url");
		Response responce=given()
				.pathParam("username", username)

				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when()
				.put(put_url);
		return responce;
	}
	public static Response deleteUser(String username)
	{
		String delete_Url=getUrl().getString("delete_url");
		Response responce=given()
				.pathParam("username", username)
				.when()
				.delete(delete_Url);
		return responce;
	}

}
