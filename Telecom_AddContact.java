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
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class Telecom_AddContact {
	 
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
		    public void AddcontactTest() {
		 // Base API URL
      RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com/contacts";
       // JSON payload for adding a pet
      String userPayload = "{\n" +
              "  \"firstName\": \"John\",\n" +
              "  \"lastName\": \"Doe\",\n" +
              "  \"birthdate\": \"1970-01-01\",\n" +
              "  \"email\": \"jdoe@fake.com\",\n" +
              "  \"phone\": \"8005555555\",\n" +
              "  \"street1\": \"1 Main St.\",\n" +
              "  \"street2\": \"Apartment A\",\n" +
              "  \"city\": \"Anytown\",\n" +
              "  \"stateProvince\": \"KS\",\n" +
              "  \"postalCode\": \"12345\",\n" +
              "  \"country\": \"USA\"\n" +
              "}";
      String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2N2NmMjRjMzMyZWFmNzAwMTM3YTU4N2EiLCJpYXQiOjE3NDE2Mjg2MTF9.oGzI1uEumXNoJDwtkszpFfFhQ1dd1TTsUCHLfiEhaaY";
      // Send POST request to add pet
      Response response = given()
              .contentType(ContentType.JSON)
              .accept(ContentType.JSON)
              .header("Authorization", "Bearer " + token)
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
		    }
      @AfterSuite
      public void tearDown() {
          extent.flush();  // Generate the report
      }

      // Selenium WebDriver setup (optional)
     // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver"); // Update with correct path
     
  
}
