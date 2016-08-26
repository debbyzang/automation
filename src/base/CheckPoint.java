package base;

import java.util.List;

import org.testng.Reporter;
import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;

public class CheckPoint extends Assertion{
	int flag ;
	private String caseName = "";
	
	public void equals(int actual, int expect){
		try {
			assertEquals(actual, expect);
		} catch (Error e) {}
	}
	
	public CheckPoint(String caseName){
		this.caseName = caseName ;
	}
	
	public void equals(int actual, int expect, String message){
		try {
			assertEquals(actual, expect);
		} catch (Error e) {}
	}
	
	public void equals(String actual, String expect){
		try {
			assertEquals(actual, expect);
		} catch (Error e) {
		}
	}
	
	public void equals(boolean actual, boolean expect){
		try {
			assertEquals(actual, expect);
		} catch (Error e) {
		}
	}
	
	public void equals(boolean actual, boolean expect,String message){
		try {
			assertEquals(actual, expect);
		} catch (Error e) {
		}
	}
	
	public void notEquals(String actual , String expect , String message){
		try {
			assertNotEquals(actual, expect);
		} catch (Error e) {}
	}
	
	public void notEquals(List<String> actuals , String expect , String message){
		if(actuals.size()!=0){
			for (String actual: actuals) {
				try {
					assertNotEquals(actual , expect ,  message);
				} catch (Error e) {}
			}
		}else{
			System.out.println("��麯��:ʵ�ʽ�� ���� ����Ϊ��!");
		}
		
	}
	
	@Override
	public void onAssertSuccess(IAssert assertCommand){
		System.out.println("[log]:���Գɹ� :ʵ�ʽ����" + assertCommand.getActual() + "  Ԥ�ڽ����" + assertCommand.getExpected());
	}
	
	@SuppressWarnings("null")
	public void onAssertFailure(IAssert assertCommand){
		String massage = "";
		if(assertCommand.getMessage()!=null){
			massage = assertCommand.getMessage();
		}
		
		System.out.println("[log]:����ʧ�� :ʵ�ʽ����" + assertCommand.getActual() + "Ԥ�ڽ����" + assertCommand.getExpected());
		flag++ ;
	}
	public void results(String message){
		org.testng.Assert.assertEquals(flag, 0);
		System.out.println("Report : " + message);
		WebSuite.resultLog.add(caseName + ": "+ message);    //��̬����̳�IReporter
		Reporter.log(caseName + ": "+ message);  //ʹ��extentreporters ���
	}
}
