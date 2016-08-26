package listener;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import base.DriverFactory;

/**
 * @author Debby
 *ʧ�����ܻ���
 */
@Test
public class TestRetryAnalyzer implements IRetryAnalyzer{
	private int retrycount = 1;
	private int retrytime = 3;
	@Override
	public boolean retry(ITestResult result) {
		if(retrycount <= retrytime){
			result.setAttribute("Retry", retrycount);
			System.out.println("����" + result.getName() + "���ڽ��е�" + retrycount + "��ʧ������");
			retrycount++;
			return true;
		}
		return false;
	}

}
