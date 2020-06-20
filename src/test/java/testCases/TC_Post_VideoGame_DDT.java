package testCases;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import utilities.XLUtils;

public class TC_Post_VideoGame_DDT {
	
	public int port = 8080;
	public String URI = "http://localhost:"+port+"/app";
		
	@Test(priority=1,dataProvider="dp")

	public void test_addNewVideGame(String id , String name , String releaseDate , String reviewScore ,String category , String rating)
	{
		
		HashMap data = new HashMap();
		data.put("id", id);
		data.put("name", name);
		data.put("releaseDate" , releaseDate);
		data.put("reviewScore", reviewScore);
		data.put("category", category);
		data.put("rating", rating);
		
		Response res=
		given()
			.contentType("application/json")
			.body(data)
		.when()
		 	.post(URI+"/videogames")
		 .then()
		 	.statusCode(200)
		 	.extract().response();
		
		String JSONData =res.asString();
		Assert.assertEquals(JSONData.contains("Record Added Successfully") , true);
	}
	
	@DataProvider(name="dp")
	
	public String[] [] getData() throws IOException{
		
		String path = System.getProperty("user.dir")+"/testData/VideoGameAPIData.xlsx"; 
		
		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int colcount = XLUtils.getCellCount(path, "Sheet1", 1);
		
		String apidata[][] = new String[rownum][colcount];
		
		
		for(int i=1; i<=rownum;i++)
			
		{
			for(int j=0; j<colcount;j++) {
				
				apidata[i-1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
						
			}
		}
		
		return apidata;
		
		
	}

}
