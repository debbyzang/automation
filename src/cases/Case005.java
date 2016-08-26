package cases;

import java.util.Random;

import org.testng.annotations.Test;

import base.WebSingleSuite;

public class Case005 extends WebSingleSuite{
	
	@Test(retryAnalyzer=listener.TestRetryAnalyzer.class)
	public void case005(){
		System.out.println("run Case005-case005");
		for(int i = 0 ; i<10 ; i++){
			check.equals(new Random().nextInt(2), 0);
		}
		check.results("验证成功");
	}
}
