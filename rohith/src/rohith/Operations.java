package rohith;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Operations extends BaseClass {
	WebElement element = null;
public WebElement find(By locator){
	xpath = locator;
	element = driver.findElement(locator);
	if(element!=null){
		xpath = null;
	}
	return element;
}

public void updateXpath(){
	System.out.println("update xpath");
	File file = new File("/Users/rohith/Documents/rohith/changeInDom.txt");
	FileWriter fs;
	try {
		fs = new FileWriter(file);
		fs.write(driver.getPageSource());
		fs.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	diff obj = new diff();
	obj.diff("/Users/rohith/Documents/rohith/lastSuccessDom.txt", "/Users/rohith/Documents/rohith/changeInDom.txt");
	System.out.println(xpath);
	
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
			
//			System.out.println(xpath.toString().replace(newData, replaceable));
		}
	}
	smartXpath = data.toString().replace(newData, replaceable);
	System.out.println(smartXpath);
	String xpathsFile = System.getProperty("user.dir") + "/src/Locators/xpaths.properties";
	 
	File fileToBeModified = new File(xpathsFile);
    
    String oldContent = "";
     
    BufferedReader reader = null;
     
    FileWriter writer = null;
     
    try
    {// /Users/rohith/Desktop/OldAutomation/Workspace/rohith/src/Locators/xpaths.txt
        reader = new BufferedReader(new FileReader(fileToBeModified));
         
        //Reading all the lines of input text file into oldContent
         
        String line = reader.readLine();
         
        while (line != null) 
        {
            oldContent = oldContent + line + System.lineSeparator();
             
            line = reader.readLine();
        }
         
        //Replacing oldString with newString in the oldContent
         
        String newContent = oldContent.replace(data, smartXpath);
         
        //Rewriting the input text file with newContent
         
        writer = new FileWriter(fileToBeModified);
         
        writer.write(newContent);
    }
    catch (IOException e)
    {
        e.printStackTrace();
    }
    finally
    {
        try
        {
            //Closing the resources
             
            reader.close();
             
            writer.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    

}
}
