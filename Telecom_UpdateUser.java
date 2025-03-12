package com.rest.get;
import io.restassured.RestAssured;
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
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class Telecom_UpdateUser {
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
    public void UpdateUserTest() {
		String userPayload = "{\n" +
                "    \"firstName\": \"Updated1\",\n" +
                "    \"lastName\": \"Username1\",\n" +
                "    \"email\": \"reena@gmail.com\",\n" +
                "    \"password\": \"myNewPassword\"\n" +
                "}";
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2N2NmMjRjMzMyZWFmNzAwMTM3YTU4N2EiLCJpYXQiOjE3NDE2Mjg2MTF9.oGzI1uEumXNoJDwtkszpFfFhQ1dd1TTsUCHLfiEhaaY";
		Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(userPayload)
                .when()
                .patch("https://thinking-tester-contact-list.herokuapp.com/users/me")
                .then()
                .statusCode(200)  // Assert response status is 200 OK
                .extract()
                .response();

        // Print response body
        System.out.println("Response Body: " + response.getBody().asString());
        
	}
    @AfterSuite
    public void tearDown() {
        extent.flush();  // Generate the report
    }
}
