package curaHealthCare.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import curaHealthCare.abstractComponents.AbstractComponents;

public class BookedAppointmentPage extends AbstractComponents {

	public WebDriver driver;
	
	public BookedAppointmentPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "div[class='col-xs-12 text-center'] h2") private WebElement confirmationMessage;
	
	public String getConfirmationMessage() {
		return confirmationMessage.getText();
	}
}
