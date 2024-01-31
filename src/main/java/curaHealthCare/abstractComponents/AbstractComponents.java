package curaHealthCare.abstractComponents;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import curaHealthCare.pageObjects.HistoryPage;
import curaHealthCare.pageObjects.LandingPage;

public class AbstractComponents {

	public WebDriver driver;
	private WebDriverWait wait;
	
	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(linkText = "Home") private WebElement homeNavbar;
	@FindBy(linkText = "History") private WebElement historyNavbar;
	@FindBy(css = ".col-sm-offset-2") private WebElement historyList;
	@FindBy(id = "menu-toggle") private WebElement navigation;
	
	public LandingPage goTohomePage() {
		navigation.click();
		homeNavbar.click();
		LandingPage landingPage = new LandingPage(driver);
		return landingPage;
	}
	
	public HistoryPage goToHistoryPage() {
		navigation.click();
		historyNavbar.click();
		waitForElementToVisible(historyList);
		HistoryPage historyPage = new HistoryPage(driver);
		return historyPage;
	}
	
	public void scrollIntoView(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public void waitForElementToVisible(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}
}
