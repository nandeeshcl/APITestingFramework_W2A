package com.practise.RestAssuredAPITestingFramework.TestCases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.practise.APITestingFramework.APIs.CreateCustomerAPI;
import com.practise.APITestingFramework.listeners.ExtentListeners;
import com.practise.APITestingFramework.utils.DataUtil;
import com.practise.RestAssuredAPITestingFramework.setup.BaseClass;

import io.restassured.response.Response;

public class TC_01_Create_Customer extends BaseClass {

	@Test(dataProviderClass = DataUtil.class, dataProvider = "data", priority = 2)
	public void validateCreateCustomerWithInValidSecretKey(Hashtable<String, String> data) {

		//call sendPostRequestToCreateCustomerAPIWithInValidSecretKey and get response from it
		Response response = CreateCustomerAPI.sendPostRequestToCreateCustomerAPIWithInValidSecretKey(data);
		response.prettyPrint();
		
		ExtentListeners.testReport.get().info("Response is: "+response.prettyPrint());
		
		// Assert status code
		Assert.assertEquals(response.getStatusCode(), 401);
		
	}

	@Test(dataProviderClass = DataUtil.class, dataProvider = "data", priority = 1)
	public void validateCreateCustomerWithValidSecretKey(Hashtable<String, String> data) {

		//call sendPostRequestToCreateCustomerAPIWithValidSecretKey and get response from it
		Response response = CreateCustomerAPI.sendPostRequestToCreateCustomerAPIWithValidSecretKey(data);

		ExtentListeners.testReport.get().info("Response is: "+response.prettyPrint());
		// Assert status code
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
