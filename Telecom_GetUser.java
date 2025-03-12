package com.rest.get;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matcher.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class Telecom_GetUser {
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
    public void GetuserTest() {
        // Base URL
        RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";

        // Bearer Token (Replace with a valid token)
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2N2NmMjRjMzMyZWFmNzAwMTM3YTU4N2EiLCJpYXQiOjE3NDE2Mjg2MTF9.oGzI1uEumXNoJDwtkszpFfFhQ1dd1TTsUCHLfiEhaaY";

        // Sending GET request to fetch user profile
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .accept(ContentType.JSON)
        .when()
                .get("/users/me") // Endpoint
        .then()
                .statusCode(200) // Validate status code is 200 OK
                .body("firstName", notNullValue()) // Validate firstName exists
                .body("lastName", notNullValue())  // Validate lastName exists
                .body("email", containsString("@")) // Ensure email format
                .extract().response();

        // Print response
        System.out.println("Response: " + response.asPrettyString());
    }
    @AfterSuite
    public void tearDown() {
        extent.flush();  // Generate the report
    }

}
