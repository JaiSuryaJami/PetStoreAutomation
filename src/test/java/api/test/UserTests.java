package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {

	  Faker faker;
	  User userpayLoad;
	  
	  public Logger logger;
	  
	  @BeforeClass
	  public void setUpData()
	  {
		  faker = new Faker();
		  userpayLoad = new User();
		  
		  userpayLoad.setId(faker.idNumber().hashCode());
		  userpayLoad.setUsername(faker.name().username());
		  userpayLoad.setFirstname(faker.name().firstName());
		  userpayLoad.setLastname(faker.name().lastName());
		  userpayLoad.setEmail(faker.internet().safeEmailAddress());
		  userpayLoad.setPassword(faker.internet().password(5, 10));
		  userpayLoad.setPhone(faker.phoneNumber().cellPhone());
		  
		  //logs
		  
		  logger = LogManager.getLogger(this.getClass());
	  }
	  
	  @Test(priority=1)
	  public void testPostUser()
	  {
		  logger.info("*******Creating User********");
		  Response response = UserEndPoints.createUser(userpayLoad);
		  response.then().log().all();
		  Assert.assertEquals(response.getStatusCode(), 200);
		  logger.info("*******User Created********");

	  }
	  
	  @Test(priority=2)
	  public void testGetUser()
	  {
		  logger.info("*******Reading User Info********");
		  Response response = UserEndPoints.readUser(this.userpayLoad.getUsername());
		  response.then().log().all();
		  Assert.assertEquals(response.getStatusCode(), 200);
		  logger.info("*******User Info is displayed ********");

	  }
	  
	  @Test(priority=3)
	  public void testUpdateUser()
	  {
		  logger.info("*******Updating User********");
		  userpayLoad.setFirstname(faker.name().firstName());
		  userpayLoad.setLastname(faker.name().lastName());
		  userpayLoad.setEmail(faker.internet().safeEmailAddress());
		  
		  Response response = UserEndPoints.updateUser(this.userpayLoad.getUsername(), userpayLoad);
		  response.then().log().body();
		  Assert.assertEquals(response.getStatusCode(), 200);
		  
		  logger.info("*******User Updated********");

		  
		  //checking data after update
		  
		  Response responseAfterUpdate = UserEndPoints.readUser(this.userpayLoad.getUsername());
		  Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		  
	  }
	  
	  @Test(priority=4)
	  public void testDeleteUser()
	  {
		  logger.info("*******Deleting User********");

		  Response response = UserEndPoints.deleteUser(this.userpayLoad.getUsername());
		  Assert.assertEquals(response.getStatusCode(), 200);
		  
		  logger.info("*******User Deleted********");

	  }
	  
}
