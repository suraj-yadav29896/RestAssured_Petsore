package api_tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api_endpoints.UserEndPoint2Proprty;
import api_payloads.User;
import api_utilities.DataProviders;
import io.restassured.response.Response;

public class GenralClassForRunProperty_FileURL {
	
	// this class using run test cases by using property file and UserEndPoint2Proprty class
	// Test001=UserTest
	User userpayload;
	Faker faker;

	@BeforeClass
	public void setup()
	{
		userpayload=new User();
		faker=new Faker();
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstname(faker.name().firstName());
		userpayload.setLastname(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password(5,10));
		userpayload.setPhoneNo(faker.phoneNumber().cellPhone());
	}
	@Test(priority=1)
	public void testPostUser()
	{
		Response responce=UserEndPoint2Proprty.createuser(userpayload);
		responce.then().log().all();
		Assert.assertEquals(responce.statusCode(),200);
	}
	@Test(priority=2)
	public void testGetUser()
	{
		Response responce=UserEndPoint2Proprty.readUser(this.userpayload.getUsername());
		responce.then().log().all();
		Assert.assertEquals(responce.statusCode(),200);
	}
	@Test(priority=3)
	public void testupdateUser()
	{
		// create data for upadte
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstname(faker.name().firstName());
		userpayload.setEmail(faker.internet().safeEmailAddress());

		Response responce=UserEndPoint2Proprty.updateUser(this.userpayload.getUsername(),userpayload);
		responce.then().log().body();
		Assert.assertEquals(responce.statusCode(),200);

		// cheaking data after update 
		Response responceafterUpdate=UserEndPoint2Proprty.readUser(this.userpayload.getUsername());
		Assert.assertEquals(responceafterUpdate.statusCode(),200);
	}
	@Test(priority=4)
	public void testDeleteUser()
	{
		Response responce=UserEndPoint2Proprty.deleteUser(this.userpayload.getUsername());
		Assert.assertEquals(responce.statusCode(),200);
	}



//=================================================================================================
   //Test002= DataDrivenTest

	@Test(priority=5,dataProvider="UserData",dataProviderClass=DataProviders.class)
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

		Response responce=UserEndPoint2Proprty.createuser(payload);
		Assert.assertEquals(responce.getStatusCode(), 200);

	}
	@Test(priority=6,dataProvider="Usernames",dataProviderClass=DataProviders.class)
	public void testdeleteuserByname(String username)
	{

		Response responce=UserEndPoint2Proprty.deleteUser(username);
		Assert.assertEquals(responce.getStatusCode(), 200);
	}

}
