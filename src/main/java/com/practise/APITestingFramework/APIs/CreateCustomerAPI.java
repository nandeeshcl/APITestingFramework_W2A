package com.practise.APITestingFramework.APIs;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import com.practise.RestAssuredAPITestingFramework.setup.BaseClass;

import io.restassured.response.Response;

public class CreateCustomerAPI extends BaseClass {

	
	//method send post request to create customer and return back response
	public static Response sendPostRequestToCreateCustomerAPIWithValidSecretKey(Hashtable<String, String> data) {
		
		Response response = given().auth().basic(config.getProperty("validToken"), "")
				.formParam("name", data.get("name")).formParam("email", data.get("email"))
				.formParam("description", data.get("description")).formParam("address[line1]", "Google")
				.formParam("preferred_locales[0]", "Yes").formParam("balance", 1000).post("customers");

		// to print logs in report
		/*
		 * ExtentListeners.testReport.get().info(data.toString());
		 * ExtentListeners.testReport.get().createNode("Test");
		 * ExtentListeners.testReport.get().info("Test");
		 */

		return response;

	}

	//method send post request to create customer and return back response
	public static Response sendPostRequestToCreateCustomerAPIWithInValidSecretKey(Hashtable<String, String> data) {
		
		Response response = given().auth().basic("sk_test_rUdaFNxpSc5PwPeGK6WWryNT00UMqhZ2FmInvalid", "")
				.formParam("name", data.get("name")).formParam("email", data.get("email"))
				.formParam("description", data.get("description")).formParam("address[line1]", "Google")
				.formParam("preferred_locales[0]", "Yes").formParam("balance", 1000)
				.post("https://api.stripe.com/v1/customers");

		return response;

	}

}
