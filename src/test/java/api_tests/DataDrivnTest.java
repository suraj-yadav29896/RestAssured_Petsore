package api_tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import api_endpoints.UserEndPoints;
import api_payloads.User;
import api_utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivnTest {
	
	@Test(priority=1,dataProvider="UserData",dataProviderClass=DataProviders.class)
	public void testPostusers(String userid,String username,String userfn,String userln,String email,String pwd,String ph)
	{
		User payload=new User();
		payload.setId(Integer.parseInt(userid));
		payload.setUsername(username);
		payload.setFirstname(userfn);
		payload.setLastname(userln);
		payload.setEmail(email);
		payload.setPassword(pwd);
		payload.setPhoneNo(ph);

		Response responce=UserEndPoints.createuser(payload);
		Assert.assertEquals(responce.getStatusCode(), 200);

	}
	@Test(priority=2,dataProvider="Usernames",dataProviderClass=DataProviders.class)
	public void testdeleteuserByname(String username)
	{

		Response responce=UserEndPoints.deleteUser(username);
		Assert.assertEquals(responce.getStatusCode(), 200);
	}

}
