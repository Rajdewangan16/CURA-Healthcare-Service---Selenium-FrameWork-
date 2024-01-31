package curaHealthCare.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import curaHealthCare.abstractComponents.AbstractComponents;

public class LoginPage extends AbstractComponents {

	public WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id = "txt-username") private WebElement userName;
	@FindBy(id = "txt-password") private WebElement userPassword;
	@FindBy(id = "btn-login") private WebElement loginBtn;
	@FindBy(id = "combo_facility") private WebElement facility;
	@FindBy(css = ".lead.text-danger") private WebElement errorMessage;
	
	public MakeAppointmentPage loginApplication(String name , String password) {
		waitForElementToVisible(loginBtn);
		userName.sendKeys(name);
		userPassword.sendKeys(password);
		loginBtn.click();
		MakeAppointmentPage makeAppointmentPage = new MakeAppointmentPage(driver);
		return makeAppointmentPage;
	}
	
	public String getLoginErrorMessage() throws InterruptedException {
		waitForElementToVisible(errorMessage);
		return errorMessage.getText();
	}
}
