package listener;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import base.DriverFactory;

/**
 * @author Debby
 *失败重跑机制
 */
@Test
public class TestRetryAnalyzer implements IRetryAnalyzer{
	private int retrycount = 1;
	private int retrytime = 3;
	@Override
	public boolean retry(ITestResult result) {
		if(retrycount <= retrytime){
			result.setAttribute("Retry", retrycount);
			System.out.println("用例" + result.getName() + "正在进行第" + retrycount + "次失败重跑");
			retrycount++;
			return true;
		}
		return false;
	}

}
