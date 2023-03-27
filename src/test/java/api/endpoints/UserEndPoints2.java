package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints2 {
	
	
	     public static ResourceBundle getUrl()
	       {
	    	   ResourceBundle routes = ResourceBundle.getBundle("routes");
	    	   return routes;
	       }
	
	     public static Response createUser(User payload)
	      {
	    	 String postURL = getUrl().getString("post_url");
	    	 
	    	  
	    	Response response =  given()
	    	  .contentType(ContentType.JSON)
	    	  .accept(ContentType.JSON)
	    	  .body(payload)
	    	  .when()
	    	  .post(postURL);
	    	
	    	return response;
	    	  
	      }
	     
	     public static Response readUser(String userName)
	      {
	    	 String getURL= getUrl().getString("get_url");

	    	Response response =  given()
	          .pathParam("username", userName)
	    	  .when()
	    	  .get(getURL);
	    	
	    	return response;
	    	  
	      }
	     
	 	
	     public static Response updateUser(String userName, User payload)
	      {
	    	 String updateURL= getUrl().getString("update_url");

	    	Response response =  given()
	    	  .contentType(ContentType.JSON)
	    	  .accept(ContentType.JSON)
	    	  .pathParam("username", userName)
	    	  .body(payload)
	    	  .when()
	    	  .put(updateURL);
	    	
	    	return response;
	    	  
	      }
	     
	     public static Response deleteUser(String userName)
	      {
	    	 String deleteURL= getUrl().getString("delete_url");

	    	Response response =  given()
	          .pathParam("username", userName)
	    	  .when()
	    	  .delete(deleteURL);
	    	
	    	return response;
	    	  
	      }

}
