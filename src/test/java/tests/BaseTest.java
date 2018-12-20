package tests;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import utilities.Constant;
import utilities.Helper;

public class BaseTest {
	public static WebDriver driver;
	public static WebElement element;
	@BeforeClass
	@Parameters({"browser"})
	public static void setUp(@Optional("chrome") String browserName){
		if (browserName.equalsIgnoreCase("chrome")) {
			// Setting chrome driver path
			System.setProperty("webdriver.chrome.driver", Constant.ChromeDriver);
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", Constant.DownloadFolder);

			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", chromePrefs);
			options.addArguments("--test-type");
			options.addArguments("--disable-extensions"); //to disable browser extension popup
			options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			driver = new ChromeDriver(options);
		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", Constant.FirefoxDriver);
			//Create a firefox driver. All test classes use this.
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("IE"))  {
			System.setProperty("webdriver.ie.driver", Constant.IEDriver);
			//Create a IE driver. All test classes use this.
			driver = new InternetExplorerDriver();
		}
		//Maximize Window
		driver.manage().window().maximize();

		//delete all cookies
		driver.manage().deleteAllCookies();

		//Go to URL
		driver.get(Constant.GoogleURL);
	}

	@AfterMethod
	public void screenshotOnFailure(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			System.out.println("Failed!!");
			System.out.println("Taking Screenshot......");
			Helper.captureScreenshot(driver, result.getName());
		}

	}

	@AfterClass
	public static void tearDown(){
		driver.close();
	}
}
