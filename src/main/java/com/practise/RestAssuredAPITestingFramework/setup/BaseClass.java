package com.practise.RestAssuredAPITestingFramework.setup;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.practise.APITestingFramework.utils.ExcelReader;

import io.restassured.RestAssured;

public class BaseClass {

	public static Properties config; // used in all classes hence public
	private FileInputStream fis;
	public static ExcelReader excel;

	@BeforeSuite
	public void setup() throws IOException {

		// Config prop file loading
		config = new Properties();
		fis = new FileInputStream("./src\\test\\resources\\configurationFiles\\config.properties");
		config.load(fis);

		// initiate excel reader class object by specifing test data file apath
		excel = new ExcelReader("./src\\test\\resources\\testDataFiles\\StripeAPITestData.xlsx");

		// To set basepath and baseuri
		RestAssured.baseURI = config.getProperty("baseURI");
		RestAssured.basePath = config.getProperty("basePath");

	}

	@AfterSuite
	public void tearDown() {

	}

	@BeforeMethod
	public void beforeMethod(Method m) {
		/*System.out.println(m.getName() + " started execution");*/

	}

	@AfterMethod()
	public void afterMethod(ITestResult result) {
/*
		System.out.println(result.getName() + " finished execution");
		if (result.getStatus() == 1) {
			System.out.println(result.getName() + " PASS");
		} else {
			System.out.println(result.getName() + " FAILED");
		}

		System.out.println();*/

	}

}
