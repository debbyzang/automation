package cases;

import org.testng.annotations.Test;

import base.WebSuite;

public class Case002 extends WebSuite{
	@Test
	public void sendMessage() {
		
		locator.linkTo("http://www.huicewang.com/ecshop/");
		locator.sleep(2);
		locator.element("��ҳ", "���԰�").click();
		locator.sleep(3);
		locator.element("���԰�", "�����ʼ���ַ").sendKeys("test@163.com");
		locator.element("���԰�", "��������-Ͷ��").click();
		locator.element("���԰�", "����").sendKeys("��Ȼ�ⲻ��һ��������");
		locator.element("���԰�", "��������").sendKeys("123123123123");
		locator.element("���԰�", "��Ҫ����").click();
		String s = locator.element("���԰�", "�ɹ���ʾ").getText();
		String s1 = "���������ѳɹ�����, ��ȴ�����Ա�����!" ;
		
		check.assertEquals(s, s1, "����ʧ��");
		check.results("�������Գɹ�");
	}

}
