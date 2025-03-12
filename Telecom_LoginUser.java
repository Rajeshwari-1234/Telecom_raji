package com.rest.get;

import static io.restassured.RestAssured.given;
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

public class Telecom_LoginUser {
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
    public void LoginuserTest() {
		 // Base API URL
		 RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com/users/login";

	        // JSON payload for login
	        String loginPayload = "{\n" +
	                "    \"email\": \"suao@gmail.com\",\n" +
	                "    \"password\": \"myNewPassword\"\n" +
	                "}";
	        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2N2NmMjRjMzMyZWFmNzAwMTM3YTU4N2EiLCJpYXQiOjE3NDE2Mjg2MTF9.oGzI1uEumXNoJDwtkszpFfFhQ1dd1TTsUCHLfiEhaaY";
	        // Sending POST request to log in
	        Response loginResponse = given()
	                .contentType(ContentType.JSON)
	                .body(loginPayload)
	                .header("Authorization", "Bearer " + token)
	                .when()
	                .post()
	                .then()
	                .statusCode(200)
	                .extract().response();

	        // Extracting the token from the response
	       // String token = loginResponse.path("token");
	       // System.out.println("Extracted Token: " + token);

	        

  

      // Validate the response
      if (loginResponse.statusCode() == 200) {
          System.out.println("user added successfully!");
          System.out.println("Response: " + loginResponse.body().prettyPrint());
      } else {
          System.out.println("Failed to add user. Status code: " + loginResponse.statusCode());
          System.out.println(loginResponse.body().asString());
      }

      
  }
    @AfterSuite
    public void tearDown() {
        extent.flush();  // Generate the report
    }
}


