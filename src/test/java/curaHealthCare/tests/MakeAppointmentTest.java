package curaHealthCare.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import curaHealthCare.pageObjects.BookedAppointmentPage;
import curaHealthCare.pageObjects.HistoryPage;
import curaHealthCare.pageObjects.LoginPage;
import curaHealthCare.pageObjects.MakeAppointmentPage;
import curaHealthCare.testComponents.BaseTest;
import curaHealthCare.testComponents.Retry;

public class MakeAppointmentTest extends BaseTest {

	@Test(dataProvider="appointmentData" , retryAnalyzer=Retry.class)
	public void withDefaultHealthCareProgram(HashMap<String,String> input){
		
		LoginPage loginPage = landingPage.goToLoginPageWithNavBar();
		MakeAppointmentPage makeAppointmentPage = loginPage.loginApplication(input.get("user"), input.get("password"));
		makeAppointmentPage.facilitySelect(input.get("facility"));
		makeAppointmentPage.hospitalReadmission();
		makeAppointmentPage.dateSelect(input.get("day"),input.get("monthAndYear"));
		makeAppointmentPage.comment(input.get("comment"));
		BookedAppointmentPage bookedAppointmentPage = makeAppointmentPage.submitAppointment();
		
		String confirmMessage = bookedAppointmentPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Appointment Confirmation"));
		
	}
	
	@Test(dataProvider="appointmentData")
	public void withMedicaidHealthCareProgram(HashMap<String,String> input){
		
		LoginPage loginPage = landingPage.goToLoginPageWithNavBar();
		MakeAppointmentPage makeAppointmentPage = loginPage.loginApplication(input.get("user"), input.get("password"));
		makeAppointmentPage.facilitySelect(input.get("facility"));
		makeAppointmentPage.hospitalReadmission();
		makeAppointmentPage.medicaidProgram();
		makeAppointmentPage.dateSelect(input.get("day"),input.get("monthAndYear"));
		makeAppointmentPage.comment(input.get("comment"));
		BookedAppointmentPage bookedAppointmentPage = makeAppointmentPage.submitAppointment();
		
		String confirmMessage = bookedAppointmentPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Appointment Confirmation"));
	}
	
	@Test(dataProvider="appointmentData")
	public void withNoHealthCareProgram(HashMap<String,String> input){
		
		LoginPage loginPage = landingPage.goToLoginPageWithNavBar();
		MakeAppointmentPage makeAppointmentPage = loginPage.loginApplication(input.get("user"), input.get("password"));
		makeAppointmentPage.facilitySelect(input.get("facility"));
		makeAppointmentPage.hospitalReadmission();
		makeAppointmentPage.noHealthcareProgram();
		makeAppointmentPage.dateSelect(input.get("day"),input.get("monthAndYear"));
		makeAppointmentPage.comment(input.get("comment"));
		BookedAppointmentPage bookedAppointmentPage = makeAppointmentPage.submitAppointment();
		
		String confirmMessage = bookedAppointmentPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Appointment Confirmation"));
	}
	
	@Test(dataProvider="appointmentData")
	public void withoutHospitalReadmission(HashMap<String,String> input) {
		LoginPage loginPage = landingPage.goToLoginPageWithNavBar();
		MakeAppointmentPage makeAppointmentPage = loginPage.loginApplication(input.get("user"), input.get("password"));
		makeAppointmentPage.facilitySelect(input.get("facility"));
		makeAppointmentPage.dateSelect(input.get("day"),input.get("monthAndYear"));
		makeAppointmentPage.comment(input.get("comment"));
		BookedAppointmentPage bookedAppointmentPage = makeAppointmentPage.submitAppointment();
		
		String confirmMessage = bookedAppointmentPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Appointment Confirmation"));
	}
	
	@Test(dataProvider = "appointmentData" , groups = {"smoke"} , retryAnalyzer=Retry.class)
	public void bookHistory(HashMap<String,String> input) {
		LoginPage loginPage = landingPage.goToLoginPageWithNavBar();
		MakeAppointmentPage makeAppointmentPage = loginPage.loginApplication(input.get("user"), input.get("password"));
		makeAppointmentPage.facilitySelect(input.get("facility"));
		makeAppointmentPage.hospitalReadmission();
		makeAppointmentPage.dateSelect(input.get("day"),input.get("monthAndYear"));
		makeAppointmentPage.comment(input.get("comment"));
		BookedAppointmentPage bookedAppointmentPage = makeAppointmentPage.submitAppointment();
		
		HistoryPage historyPage = bookedAppointmentPage.goToHistoryPage();
		Boolean matches = historyPage.verifyBookedDisplay(input.get("facility"));
		Assert.assertTrue(matches);
	}
	
	@DataProvider(name = "appointmentData")
	public Object[][] getData() throws IOException {
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir") +
					"\\src\\test\\java\\curaHealthCare\\data\\BookAppointmentData.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)},{data.get(1)}};
	}
}
