package curaHealthCare.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import curaHealthCare.abstractComponents.AbstractComponents;

public class MakeAppointmentPage extends AbstractComponents {

	public WebDriver driver;
	
	public MakeAppointmentPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id = "combo_facility") private WebElement facility;
	@FindBy(id = "chk_hospotal_readmission") private WebElement applyHospitalReadmission;
	@FindBy(id = "radio_program_medicare") private WebElement medicareRadioBtn;
	@FindBy(id = "radio_program_medicaid") private WebElement medicaidRadioBtn;
	@FindBy(id = "radio_program_none") private WebElement noneRadioBtn;
	@FindBy(id = "txt_visit_date") private WebElement visitDate;
	@FindBy(id = "txt_comment") private WebElement comment;
	@FindBy(id = "btn-book-appointment") private WebElement bookAppointmentBtn;
	@FindBy(css = ".datepicker-days .datepicker-switch") private WebElement month;
	@FindBy(css = ".datepicker-days .next") private WebElement pickedMonth;
	@FindBy(css = "[class='day']") private List<WebElement> days;
	
	@FindBy(css = "div[class='col-xs-12 text-center'] h2") private WebElement confirmationMessage;
	
	public void facilitySelect(String facilityName) {
		
		facility.click();
		Select dropdown = new Select(facility);
		dropdown.selectByValue(facilityName);
	}
	
	public void hospitalReadmission() {
		applyHospitalReadmission.click();
	}
	
	public void medicareProgram() {
		medicareRadioBtn.click();
	}
	
	public void medicaidProgram() {
		medicaidRadioBtn.click();
	}
	
	public void noHealthcareProgram() {
		noneRadioBtn.click();
	}
	
	public void dateSelect(String day , String monthWithYear) {
		visitDate.click();
		while(!month.getText().contains(monthWithYear)) {
			pickedMonth.click();
		}
		WebElement dayPick = days.stream().filter(d -> d.getText().equalsIgnoreCase(day)).findFirst().orElse(null);
		dayPick.click();
	}
	public void comment(String cmt) {
		comment.sendKeys(cmt);
	}
	
	public BookedAppointmentPage submitAppointment() {
		bookAppointmentBtn.click();
		waitForElementToVisible(confirmationMessage);
		BookedAppointmentPage bookedAppointmentPage = new BookedAppointmentPage(driver);
		return bookedAppointmentPage;
	}
}
