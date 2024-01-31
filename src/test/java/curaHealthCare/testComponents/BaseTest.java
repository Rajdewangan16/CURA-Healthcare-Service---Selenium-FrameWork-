package curaHealthCare.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import curaHealthCare.pageObjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	protected LandingPage landingPage;
	public WebDriver initializeDriver() throws Exception {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + 
				"\\src\\main\\java\\curaHealthCare\\resources\\GlobalData.properties");
		prop.load(fis);
		
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");
		
		if(browserName.contains("chrome")) {
			driver = new ChromeDriver();
		}
		if(browserName.contains("firefox")) {
			driver = new FirefoxDriver();
		}
		if(browserName.contains("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}
	
	public String getScreenShot(String testcaseName , WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "\\reports\\" + testcaseName + ".png" );
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "\\reports\\" + testcaseName + ".png";
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>(){
		});
		return data;
	}
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws Exception {
		initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goToCHS();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}
}
