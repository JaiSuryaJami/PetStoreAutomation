package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTests {
	
	@Test(priority=1, dataProvider="Data", dataProviderClass=DataProviders.class)
	public void testPostUser(String userID, String userName, String fName, String lName, String userEmail, String pwd, String ph)
	{
		User userPayLoad = new User();
		
		userPayLoad.setId(Integer.parseInt(userID));
		userPayLoad.setUsername(userName);
		userPayLoad.setFirstname(fName);
		userPayLoad.setLastname(lName);
		userPayLoad.setEmail(userEmail);
		userPayLoad.setPassword(pwd);
		userPayLoad.setPhone(ph);
		
    	Response response = UserEndPoints.createUser(userPayLoad);
    	Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
	@Test(priority=2, dataProvider="userNames", dataProviderClass=DataProviders.class)
	public void testDeleteUserByName(String userName)
	{
		   Response response = UserEndPoints.deleteUser(userName);
	    	Assert.assertEquals(response.getStatusCode(), 200);

	}

}
