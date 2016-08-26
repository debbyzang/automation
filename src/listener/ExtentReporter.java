package listener;

import java.io.File;

import util.DateHandler;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReporter {

	public static String reportPath = "Report/" + DateHandler.getTimestamp() + ".html";
	
	public static void main(String[] args){
		
		ExtentReports extent = new ExtentReports(reportPath);
		extent.loadConfig(new File("extent-config.xml"));
		ExtentTest test = extent.startTest("Case001","decription of Case001");
		String img=test.addScreenCapture("../test-output/snapshot/"+DateHandler.getTimestamp() + ".jpg");
		test.log(LogStatus.INFO, "test infomation",img);
		test.assignAuthor("debby");
		extent.endTest(test);
		extent.flush();
		extent.close();
//		ReporterType reporterType = null;
//		extent.startReporter(reporterType, reportPath);
//		extent.flush();
		
	}
	
}
