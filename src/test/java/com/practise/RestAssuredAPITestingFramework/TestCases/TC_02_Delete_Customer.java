package com.practise.RestAssuredAPITestingFramework.TestCases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.practise.APITestingFramework.APIs.DeleteCustomerAPI;
import com.practise.APITestingFramework.listeners.ExtentListeners;
import com.practise.APITestingFramework.utils.DataUtil;
import com.practise.APITestingFramework.utils.ValidationUtils;
import com.practise.RestAssuredAPITestingFramework.setup.BaseClass;

import static io.restassured.RestAssured.*;

import io.restassured.response.Response;

public class TC_02_Delete_Customer extends BaseClass {

	@Test(dataProviderClass = DataUtil.class, dataProvider = "data")
	public void validateDeleteCustomerWithValidID(Hashtable<String, String> data) {

		////call sendDeleteRequestToDeleteCustomerAPIWithValidID and get response from it
		Response response = DeleteCustomerAPI.sendDeleteRequestToDeleteCustomerAPIWithValidID(data);

		response.prettyPrint();
		ExtentListeners.testReport.get().info("Response is: "+response.prettyPrint());
		// Assert status code
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//Check specific key present
		//to check id key is present 
		Assert.assertTrue(ValidationUtils.jasonHasKey(response.asString(),"id"),"ID field missing");
		
		//to check send id and deleted id is same
		String ActualID=ValidationUtils.getJsonKeyValue(response.asString(),"id");
		Assert.assertEquals(ActualID,data.get("id"),"ID not matching");
		
		//To check response json is not empty
		Assert.assertFalse(ValidationUtils.responseIsEmpty(response.asString()),"Empty response");
		
		//to check delete value is not null
		Assert.assertFalse(ValidationUtils.keyValueIsNull(response.asString(),"deleted"),"deleted field is null");
		
	}

}
