package hariKrishna.DataDriven;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Utility {
	
	public WebDriver driver;
	
	public WebDriver invokeBrowser() throws MalformedURLException {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setBrowserName("chrome");
		caps.setPlatform(Platform.ANY);
		driver = new RemoteWebDriver(new URL("http://192.168.0.121:4444"),caps);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	@BeforeMethod
	public WebDriver launch() throws MalformedURLException {
		invokeBrowser();
		driver.get("https://login.salesforce.com/");
		return driver;
	}

	@AfterMethod
	public void close() {
		driver.close();
	}
}
