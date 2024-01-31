package curaHealthCare.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import curaHealthCare.abstractComponents.AbstractComponents;

public class HistoryPage extends AbstractComponents {

	public WebDriver driver;
	
	public HistoryPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "facility") private List<WebElement> facilityNames;
	
	public Boolean verifyBookedDisplay(String facilityName) {
		Boolean match = facilityNames.stream().anyMatch(fnm -> fnm.getText().equalsIgnoreCase(facilityName));
		return match;
	}

}
