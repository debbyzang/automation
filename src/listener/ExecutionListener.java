package listener;
import java.util.Iterator;

import org.openqa.selenium.WebDriver;
import org.testng.*;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.DriverFactory;
import base.Log;
import base.TestBase;
//@Test
public class ExecutionListener implements ITestListener{
	
	protected ExtentReports report;
	protected static ExtentTest test;

	/* 
	 * 失败次数统计去重
	 * @see org.testng.ITestListener#onFinish(org.testng.ITestContext)
	 */
	@Override
	public void onFinish(ITestContext context) {
		System.out.println("finish============");
		
		Iterator<ITestResult> failTests = context.getFailedTests().getAllResults().iterator();
		
		while(failTests.hasNext()){
			ITestResult result = failTests.next();
			ITestNGMethod method = result.getMethod();
			
			if(context.getFailedTests().getResults(method).size()>1){
				failTests.remove();
			}
		}
			System.out.println("total failed number :" + context.getFailedTests().getAllResults().size());
			System.out.println("total passed number :" + context.getPassedTests().getAllResults().size());
			System.out.println("total skipped number :" + context.getSkippedTests().getAllResults().size());
	}

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onStart(org.testng.ITestContext)
	 */
	@Override
	public void onStart(ITestContext context) {
		System.out.println("测试开始执行，为所有测试用例添加失败重跑机制");
//		test.assignAuthor("Debby");
		report = TestBase.getExtent();
		for (ITestNGMethod method : context.getAllTestMethods()) {
			method.setRetryAnalyzer(new TestRetryAnalyzer());
			Log.info(method.getMethodName() + "->SetRetry");
		}
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
//		System.out.println("failsize-------" + Reporter.getOutput(result).size());
//		System.out.println(Reporter.getOutput(result).toString());
		test.log(LogStatus.FAIL,Reporter.getOutput(result).get(0));
		report.endTest(test);
		report.flush();
//		AutoCapture.takeScreenshot(driver);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(LogStatus.SKIP, Reporter.getOutput(result).get(0));
		report.endTest(test);
		report.flush();
		
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		Log.info("TestStart!");
		test = report.startTest(arg0.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
//		System.out.println("passsize------" + Reporter.getOutput(result).size());
//		System.out.println(Reporter.getOutput(result).toString());
		test.log(LogStatus.PASS, Reporter.getOutput(result).get(0));
		report.endTest(test);
		report.flush();
	}

}
