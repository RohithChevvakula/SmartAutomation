package rohith;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BaseClass {
	static Properties xpaths;
	static HashMap<String, String> map = new HashMap<String, String>();
	static By xpath = null;
	static String smartXpath = null;
	static WebDriver driver;
	boolean canBeHealed = false;
	static String newData = null;
	static String replaceable = null;
	
	public BaseClass() {
		String xpathsFile = System.getProperty("user.dir") + "/src/Locators/xpaths.properties";
		xpaths = getProperty(xpathsFile);
	}
	
	static Properties getProperty(String filePath) {
		Properties property = new Properties();
		File file = new File(filePath);
		InputStream input = null;
		try {
			input = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			property.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return property;
	}
	
	
	public static String suggestNewXpath() {
		String data = xpath.toString();
		data = data.substring(data.indexOf("/") + 2).trim();

		Pattern p = Pattern.compile("'([^\"]*)'");
		Matcher m = p.matcher(data);
		while (m.find()) {
		  newData = m.group(1);
		}
		
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if(entry.getKey().contains(newData)){
				Pattern p1 = Pattern.compile("\"([^\"]*)\"");
				Matcher m1 = p1.matcher(entry.getValue().toString());
				while (m1.find()) {
					replaceable = m1.group(1);
				}
				
//				System.out.println(xpath.toString().replace(newData, replaceable));
			}
		}
		return xpath.toString().replace(newData, replaceable);
	}
}

