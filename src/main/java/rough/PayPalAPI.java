package rough;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practise.APITestingFramework.pojoClass.Orders;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PayPalAPI {

	String accessToken = "";
	String orderID;

	@Test(priority = 1)
	public void getAccessToken() {
		
		System.out.println("---GET ACCESS TOKEN ----");
		
		
		RestAssured.baseURI = "https://api.sandbox.paypal.com";

		Response response = given().auth().preemptive()
				.basic("AU2UBK2N4aW_IB9b52LbhmW9vHjI8LoqsN2quCg7ucjKmmUBMQ4ME6AsiHQefUHEy_o3c5vhQcndfywX",
						"ELvq2XEKelExl3WbzZDk81Ai3nqFQ5Sr8R31waVz0hayZIR-504wgeF0UxaDbQFiYtCUvZTgXmNLsQtL")
				.param("grant_type", "client_credentials").post("/v1/oauth2/token");

		response.prettyPrint();

		accessToken = response.jsonPath().get("access_token").toString();

		System.out.println("Access token is : " + accessToken);
		
		Assert.assertEquals(response.getStatusCode(),200);

	}
	
	
	@Test(priority=2,dependsOnMethods="getAccessToken")
	public void createOrder() throws JsonParseException, JsonMappingException, IOException
	{
		System.out.println("---CREATE ORDER ----");
		
		
		RestAssured.baseURI = "https://api.sandbox.paypal.com";
		
		
		//Read JSON File
		byte[] jsonData	=Files.readAllBytes(Paths.get(".\\src\\test\\resources\\jsonFiles\\Orders.json"));
		ObjectMapper mapper=new ObjectMapper();
		Orders orders=mapper.readValue(jsonData,Orders.class);
		
		
		Response response=given().auth().oauth2(accessToken).contentType(ContentType.JSON).body(orders).post("/v2/checkout/orders");
		response.prettyPrint();
		
		String status=response.jsonPath().get("status").toString();
		
		System.out.println("Status is: "+status);
		
		Assert.assertEquals(status,"CREATED");
		
		//System.out.println("ORDER ID is: "+orderID);
		
		orderID=response.jsonPath().get("id").toString();
		
	}
	
	
	@Test(priority=3,dependsOnMethods= {"getAccessToken","createOrder"})
	public void getOrder()
	{
		System.out.println("---GET ORDER ----");
		System.out.println("ORDER ID is: "+orderID);
		
		RestAssured.baseURI = "https://api.sandbox.paypal.com";
		
		Response response=given().auth().oauth2(accessToken).contentType(ContentType.JSON).get("/v2/checkout/orders/"+orderID);
		
		response.prettyPrint();
		
		Assert.assertEquals(response.getStatusCode(),200);
		
	}
	
	

}
