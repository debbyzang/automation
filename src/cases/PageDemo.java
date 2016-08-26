package cases;

import org.testng.annotations.Test;

import base.WebSingleSuite;
import pages.LoginPage;

public class PageDemo extends WebSingleSuite{

	@Test
	public void test(){
		LoginPage lg = new LoginPage();
		lg.loginAction("debby", "123123");
	}
}
