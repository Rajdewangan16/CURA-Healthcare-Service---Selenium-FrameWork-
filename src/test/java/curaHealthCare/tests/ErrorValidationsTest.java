package curaHealthCare.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import curaHealthCare.pageObjects.LoginPage;
import curaHealthCare.testComponents.BaseTest;
import curaHealthCare.testComponents.Retry;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups = {"errorHandling"} , retryAnalyzer=Retry.class)
	public void verifyLoginErrorMessage() throws InterruptedException {
		
		LoginPage loginPage = landingPage.goToLoginPageWithNavBar();
		loginPage.loginApplication("Bob Smith","wrongpass");
		Assert.assertEquals(loginPage.getLoginErrorMessage(),"Login failed! Please ensure the username and password are valid.");
	}
	
	
	@DataProvider(name = "errorValidationData")
	public Object[][] getData() throws IOException {
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir") +
					"\\src\\test\\java\\curaHealthCare\\data\\BookAppointmentData.json");
		
		return new Object[][] {{data.get(0)}};
	}
}
