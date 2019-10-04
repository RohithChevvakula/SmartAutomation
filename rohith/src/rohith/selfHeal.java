package rohith;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.xml.sax.SAXException;
import junit.framework.AssertionFailedError;

public class selfHeal extends BaseClass {
	static boolean flag = false;

	public static void main(String[] args) throws IOException, SAXException {
		try {
			System.setProperty("webdriver.chrome.driver", "/Users/rohith/Documents/chromedriver");
			driver = new ChromeDriver();
			driver.get("file:///Users/rohith/Documents/selfHeal2.html");
			driver.manage().window().maximize();
			Operations ops = new Operations();
			WebElement addressField = ops.find(By.xpath(xpaths.getProperty("address")));
			String actualText = addressField.getText();
			String expectedText = "Enter your address:";
			org.testng.Assert.assertEquals(actualText, expectedText);

			flag = true;

		} catch (AssertionFailedError e) {
			System.out.println("HI");
		} finally {
			if (flag) {
				File file = new File("/Users/rohith/Documents/rohith/lastSuccessDom.txt");
				FileWriter fs = new FileWriter(file);
				fs.write(driver.getPageSource());
				System.out.println("PASSED");
				fs.close();
			} else {
				File file = new File("/Users/rohith/Documents/rohith/changeInDom.txt");
				FileWriter fs = new FileWriter(file);
				fs.write(driver.getPageSource());
				fs.close();
				diff obj = new diff();
				obj.diff("/Users/rohith/Documents/rohith/lastSuccessDom.txt", "/Users/rohith/Documents/rohith/changeInDom.txt");
				System.out.println(xpath);
				String newXpath = suggestNewXpath();
			}
			driver.quit();
		}
	}
}
