package com.rest.get;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Telecom_Adduser {
	
	    private static ExtentReports extent;
	    private static ExtentTest test;
	    @BeforeSuite
	    public void setupReport() {
	        // Set up Extent Reports
	    	ExtentSparkReporter htmlReporter = new ExtentSparkReporter("TestReport.html");
	        htmlReporter.config().setDocumentTitle("API Automation Report");
	        htmlReporter.config().setReportName("Rest Assured Test Report");
	        htmlReporter.config().setTheme(Theme.DARK);

	        extent = new ExtentReports();
	        extent.attachReporter(htmlReporter);
	    }
	    @Test
	    public void AdduserTest() {
		 // Base API URL
       RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com/users";

       // JSON payload for adding a pet
       String userPayload = "{\n" +
               "    \"firstName\": \"Test\",\n" +
               "    \"lastName\": \"User\",\n" +
               "    \"email\": \"alley@gmail.com\",\n" +
               "    \"password\": \"myPassword\"\n" +
               "}";

       // Send POST request to add pet
       Response response = given()
               .contentType(ContentType.JSON)
               .accept(ContentType.JSON)
               .body(userPayload)
               .when()
               .post();

       // Validate the response
       if (response.statusCode() == 201) {
           System.out.println("user added successfully!");
           System.out.println("Response: " + response.body().prettyPrint());
       } else {
           System.out.println("Failed to add user. Status code: " + response.statusCode());
           System.out.println(response.body().asString());
       }

       // Selenium WebDriver setup (optional)
       //System.setProperty("webdriver.chrome.driver", "path/to/chromedriver"); // Update with correct path
	    }  
   
	@AfterSuite
    public void tearDown() {
        extent.flush();  // Generate the report
    }
	}

