package listener;

import java.io.File;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	
	private static ExtentReports extentReports;
	public synchronized static ExtentReports getReports(String filePath){
		if(extentReports==null){
			extentReports = new ExtentReports(filePath,true);
			extentReports.loadConfig(new File("extent-config.xml"));
		}
		return extentReports;
	}

}
