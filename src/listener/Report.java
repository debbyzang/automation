package listener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.lift.TestContext;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.xml.XmlSuite;

import base.WebSuite;

public class Report implements IReporter{
	
	String PROJECT_NAME = "";
	String ENDTIME = "";
	int PASSED_NUM = 0 ;
	int FAILED_NUM = 0 ;
	int SKIPPED_NUM = 0 ;
	float PASSEDRATE = 0 ;
	int TOTAL_NUM = 0 ;

	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> iSuites,String outputDirectory) {
		
		PROJECT_NAME = xmlSuites.get(0).getName();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ENDTIME = "测试结束时间： " + sdf.format(new Date());
		Set<String> resultLists = iSuites.get(0).getResults().keySet();
		
		for (String key : resultLists) {
			ITestContext testContext = iSuites.get(0).getResults().get(key).getTestContext();
			PASSED_NUM = testContext.getPassedTests().size();
			FAILED_NUM = testContext.getFailedTests().size();
			SKIPPED_NUM = testContext.getSkippedTests().size();
		}
		
		TOTAL_NUM = PASSED_NUM + FAILED_NUM + SKIPPED_NUM ;
		PASSEDRATE = 100 * (PASSED_NUM / TOTAL_NUM) ;
		
		if(Double.isNaN(PASSEDRATE)){
			PASSEDRATE = 0 ;
		}
	
		StringBuffer htmlString = new StringBuffer();
		htmlString.append("<html lang = \"en\">");
		htmlString.append("<h1>" + PROJECT_NAME + "</h1>");
		htmlString.append("<p>" + ENDTIME + "</p>");
		htmlString.append("<p>成功 ：" + PASSED_NUM + "</p>");
		htmlString.append("<p>失败：" + FAILED_NUM + "</p>");
		htmlString.append("<p>跳过 ：" + SKIPPED_NUM + "</p>");
		htmlString.append("<p>总计 ：" + TOTAL_NUM + "</p>");
		htmlString.append("<p>成功率 ：" + PASSEDRATE + "%</p>");
		htmlString.append("<p>测试日志:</p>");
		for(String text :WebSuite.resultLog){
			htmlString.append("<p>" + text + "</p>");
		}
		htmlString.append("</html>");

		write("test-output/HtmlReport/" +new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".html" , htmlString.toString());
	}
	
	public static void write(String filePath , String data){
		
		File file = new File(filePath) ;
		
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("创建文件失败");
			}
		}
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
			bw.write(data);
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("写文件失败");
		}
		try {
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
