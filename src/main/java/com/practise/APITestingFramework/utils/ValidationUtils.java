package com.practise.APITestingFramework.utils;

import org.json.JSONObject;

import com.practise.APITestingFramework.listeners.ExtentListeners;
import com.practise.RestAssuredAPITestingFramework.setup.BaseClass;

public class ValidationUtils extends BaseClass {

	// to check specific key is present or not
	public static boolean jasonHasKey(String json, String key) {
		JSONObject jsonObject = new JSONObject(json);
		return jsonObject.has(key);

	}

	// to send specific value of a key
	public static String getJsonKeyValue(String json, String key) {
		JSONObject jsonObject = new JSONObject(json);
		ExtentListeners.testReport.get().info("Validatng Json has key value "+key);
		return jsonObject.get(key).toString();
	}

	// To check specific field is null
	public static boolean keyValueIsNull(String json, String key) {

		JSONObject jsonObject = new JSONObject(json);
		ExtentListeners.testReport.get().info("Validatng Is Key value is null");
		return jsonObject.isNull(key);

	}

	// to check response is empty
	public static boolean responseIsEmpty(String json) {
		JSONObject jsonObject = new JSONObject(json);
		ExtentListeners.testReport.get().info("Validatng Is response is null");
		return jsonObject.isEmpty();

	}

	// returns lenght of reposne
	public static int lengthOfResponse(String json) {
		JSONObject jsonObject = new JSONObject(json);
		ExtentListeners.testReport.get().info("Validatng legth of response");
		return jsonObject.length();
	}

}
