package curaHealthCare.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import curaHealthCare.pageObjects.LoginPage;
import curaHealthCare.testComponents.BaseTest;

public class LoginTest extends BaseTest {

	@Test(dataProvider="loginData")
	public void loginWithNavbar(HashMap<String,String> input) {
		LoginPage loginPage = landingPage.goToLoginPageWithNavBar();
		loginPage.loginApplication(input.get("user"),input.get("password"));
	}
	
	@Test(dataProvider="loginData" , groups = {"smoke"})
	public void loginWithMakeAppointLink(HashMap<String,String> input) {
		LoginPage loginPage = landingPage.goToLoginPageWithMakeAppointmentLink();
		loginPage.loginApplication(input.get("user"),input.get("password"));
	}
	
	@DataProvider(name = "loginData")
	public Object[][] getData() throws IOException {
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir") +
					"\\src\\test\\java\\curaHealthCare\\data\\BookAppointmentData.json");
		
		return new Object[][] {{data.get(0)}};
	}
}
