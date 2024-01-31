package curaHealthCare.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import curaHealthCare.abstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	public WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id = "menu-toggle") private WebElement navigation;
	@FindBy(linkText = "Login") private WebElement loginLink;
	@FindBy(id = "btn-make-appointment") private WebElement makeAppointmentBtn;
	@FindBy(id = "btn-login") private WebElement loginBtn;
	
	public LoginPage goToLoginPageWithNavBar() {
		navigation.click();
		loginLink.click();
		waitForElementToVisible(loginBtn);
		LoginPage loginPage = new LoginPage(driver);
		return loginPage;
	}
	
	public LoginPage goToLoginPageWithMakeAppointmentLink() {
		makeAppointmentBtn.click();
		waitForElementToVisible(loginBtn);
		LoginPage loginPage = new LoginPage(driver);
		return loginPage;
	}
	
	public void goToCHS() {
		driver.get("https://katalon-demo-cura.herokuapp.com/");
	}

}
