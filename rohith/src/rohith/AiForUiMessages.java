package rohith;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AiForUiMessages extends BaseClass {
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
		driver.get("file:///Users/rohith/Documents/login1.html");
		driver.manage().window().maximize();
	}

	@Test
	public void textChangedCase() throws InterruptedException {
		String messagesFile = System.getProperty("user.dir") + "/src/Locators/messages.properties";
		d = getProperty(messagesFile);
		Operations ops = new Operations();
		
		WebElement EnterAddress = ops.find(By.xpath(xpaths.getProperty("addressField")));
		EnterAddress.sendKeys(d.getProperty("enterAddress").toString());
		Thread.sleep(2000);
		
		WebElement name = ops.find(By.xpath(xpaths.getProperty("namefield")));
		name.sendKeys(d.getProperty("name").toString());
		Thread.sleep(2000);
		
		WebElement submitButton = ops.find(By.xpath(xpaths.getProperty("submit")));
		Assert.assertNotNull(submitButton);
		submitButton.click();
		Thread.sleep(2000);
		
		WebElement response = ops.find(By.xpath(xpaths.getProperty("response")));
		Assert.assertNotNull(response);

		String actualText = response.getText();
		String expectedText = d.getProperty("response");

		if (expectedText.contains(actualText)) {
			Assert.assertTrue(true, "Message is validated using smart analysing of text");
		} else {
			Assert.fail();
		}
	}

	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}
}
