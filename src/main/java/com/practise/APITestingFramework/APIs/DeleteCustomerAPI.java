package com.practise.APITestingFramework.APIs;

import com.practise.RestAssuredAPITestingFramework.setup.BaseClass;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.Hashtable;


public class DeleteCustomerAPI extends BaseClass {

	//method send delete request and return back response
	public static Response sendDeleteRequestToDeleteCustomerAPIWithValidID(Hashtable<String, String> data) {
		Response response = given().auth().basic(config.getProperty("validToken"), "").delete("customers/" + data.get("id"));

		return response;

	}

}
