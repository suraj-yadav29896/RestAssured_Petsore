package api_tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api_endpoints.UserEndPoints;
import api_payloads.User;
import io.restassured.response.Response;

public class UserTest {
	User userpayload;
	Faker faker;
	Logger logger;

	@BeforeClass
	public void setup()
	{
		logger=LogManager.getLogger(this.getClass());
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
		Response responce=UserEndPoints.createuser(userpayload);
		responce.then().log().all();
		Assert.assertEquals(responce.statusCode(),200);
		logger.info("**************post test is ended**********************");
	}
	@Test(priority=2)
	public void testGetUser()
	{
		Response responce=UserEndPoints.readUser(this.userpayload.getUsername());
		responce.then().log().all();
		Assert.assertEquals(responce.statusCode(),200);
		logger.info("**************get test is ended**********************");
	}
	@Test(priority=3)
	public void testupdateUser()
	{
		// create data for upadte
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstname(faker.name().firstName());
		userpayload.setEmail(faker.internet().safeEmailAddress());

		Response responce=UserEndPoints.updateUser(this.userpayload.getUsername(),userpayload);
		responce.then().log().body();
		Assert.assertEquals(responce.statusCode(),200);

		// cheaking data after update 
		Response responceafterUpdate=UserEndPoints.readUser(this.userpayload.getUsername());
		Assert.assertEquals(responceafterUpdate.statusCode(),200);
		logger.info("**************update test is ended**********************");
	}
	@Test(priority=4)
	public void testDeleteUser()
	{
		Response responce=UserEndPoints.deleteUser(this.userpayload.getUsername());
		Assert.assertEquals(responce.statusCode(),200);
		logger.info("**************delete test is ended**********************");
	}


}
