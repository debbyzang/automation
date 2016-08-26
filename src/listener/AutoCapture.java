package listener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class AutoCapture extends TestListenerAdapter {
	
	public static void takeScreenshot(WebDriver driver, String screenPath) {
		try{
			File srcFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File(screenPath));
		}catch(IOException e){
			System.out.println("ΩÿÕº“Ï≥£");
		}
	}
	
	public static void takeScreenshot(WebDriver driver) {
		String screenname = String.valueOf(new Date().getTime()) + ".jpg";
		File dir = new File("test-output/snapshot");
		if(!dir.exists()){
			dir.mkdirs();
		}
		String screenpath = dir.getAbsolutePath() + "/" + screenname;
		takeScreenshot(driver, screenpath);
	}
	
}
