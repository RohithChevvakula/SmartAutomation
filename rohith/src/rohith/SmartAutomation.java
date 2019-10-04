package rohith;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import rohith.Watchman;

//@Listeners(Watchman.class)
public class SmartAutomation extends BaseClass {
	static boolean flag = false;
	Properties d;

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite(ITestContext context) {
		for (ITestNGMethod method : context.getAllTestMethods()) {
			method.setRetryAnalyzer(new RetryAnalyzer());
		}
	}

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "/Users/rohith/Documents/chromedriver");
		driver = new ChromeDriver();
		driver.get("file:///Users/rohith/Documents/new.html");
		driver.manage().window().maximize();
		
		System.out.println("before test");
	}

	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void uiElementChangedCase() {
		System.out.println(System.currentTimeMillis());
		String xpathsFile = System.getProperty("user.dir") + "/src/Locators/xpaths.properties";
		d = getProperty(xpathsFile);
		System.out.println("in test");
		Operations ops = new Operations();
		WebElement addressField = ops.find(By.xpath(d.getProperty("address")));
		Assert.assertNotNull(addressField);
	}


	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}
}
