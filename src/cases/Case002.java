package cases;

import org.testng.annotations.Test;

import base.WebSuite;

public class Case002 extends WebSuite{
	@Test
	public void sendMessage() {
		
		locator.linkTo("http://www.huicewang.com/ecshop/");
		locator.sleep(2);
		locator.element("首页", "留言板").click();
		locator.sleep(3);
		locator.element("留言板", "电子邮件地址").sendKeys("test@163.com");
		locator.element("留言板", "留言类型-投诉").click();
		locator.element("留言板", "主题").sendKeys("虽然这不是一个好主题");
		locator.element("留言板", "留言内容").sendKeys("123123123123");
		locator.element("留言板", "我要留言").click();
		String s = locator.element("留言板", "成功提示").getText();
		String s1 = "您的留言已成功发表, 请等待管理员的审核!" ;
		
		check.assertEquals(s, s1, "留言失败");
		check.results("发表留言成功");
	}

}
