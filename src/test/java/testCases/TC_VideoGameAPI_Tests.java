package testCases;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TC_VideoGameAPI_Tests {
	
	public int port = 8080;
	public String URI = "http://localhost:8080//app//videogames";
	
	@Test(priority=1)
	public void test_get_post_AllVideoGames() {
		
		HashMap data = new HashMap();
		data.put("id" , "222");
		data.put("name" , "Resident Evil 222");
		data.put("releaseDate", "2005-10-01");
		data.put("reviewScore" , 85);
		data.put("category", "Shooter");
		data.put("rating", "Universal");
		
		Response res =
		given()
			.contentType("application/json")
			.body(data)
		.when()
			//.get(URI)
			.post("http://localhost:8080/app/videogames")
		.then()
			.statusCode(200)
			.log().body()
			.extract().response();
		
		String jsonString = res.asString();
		Assert.assertEquals(jsonString.contains("Record Added Successfully") , true);
	}
	
	    @Test(priority=2)
	    public void test_getVideoGame() {
	    	
	    	given()
	    	
	    	.when()
	    		.get("http://localhost:8080//app//videogames")
	    	.then()
	    		.statusCode(200)
	    		.log().body();
    	
	    }
	    
	   @Test(priority=3)
	    public void test_updateVideoGame()
	    {
	    	HashMap data1 = new HashMap();
	    	data1.put("id" , "222");
			data1.put("name" , "Resident Evil 222");
			data1.put("releaseDate", "2005-10-01");
			data1.put("reviewScore" , 100);
			data1.put("category", "Shooter");
			data1.put("rating", "Universal");
			
			given()
				.contentType("application/json")
				.body(data1)
			
			.when()
				.put("http://localhost:8080//app//videogames/222")
			
			.then()
				.log().body()
				.body("videoGame.id", equalTo("222"));  	
	    }
			
			@Test(priority=4)
			public void delete_VideoGame() 
			{
				Response res1 =
				given()
				
				.when()
					.delete("http://localhost:8080/app/videogames/222")
				
				.then()
					.statusCode(200)
					.log().body()
					.extract().response();
					
				String Jsonstrvar =res1.asString();
				Assert.assertEquals(Jsonstrvar.contains("Record Deleted Successfully"), true);
					
				
			}
	    	
	    
	

}
