package base;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import listener.ExtentManager;

import org.dom4j.Element;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import util.DateHandler;
import util.ExcelParser;
import util.XMLParser;

import com.relevantcodes.extentreports.ExtentReports;

/**
 * @author Debby
 *
 */
public class TestBase {

	private XMLParser xp ;
	private String filePath = "TestData/" + this.getClass().getSimpleName() + ".xml";   
	private Map<String, String> commonMap ;
	protected static Map<String, String> global ;
	private static String reportPath = "Report/" + DateHandler.getTimestamp() + ".html";
	private static ExtentReports extentReports;
	
	static{
		XMLParser g_xp = new XMLParser("TestData/Global.xml");   //在ant里运行需要写两个点，testNG运行一个点
		global = g_xp.getChildrenNodesByElement(g_xp.getElement("/*"));
	}
	
	public void init(){
		if(xp == null){
			xp = new XMLParser(filePath);
		}
	}
	
	@BeforeSuite
	public void beforeSuite(){
		extentReports = ExtentManager.getReports(reportPath);
		
	}
	
	@AfterSuite
	public void afterSuite(){
		extentReports.flush();
		extentReports.close();
	}
	
//	@AfterTest
//	public void afterTest(){
//		extentReports.close();
//	}
	
	public static ExtentReports getExtent(){
		return extentReports;
	}
	
	public static String getReportPath(){
		return reportPath;
	}
	
	@DataProvider
	public Object[][] getData(Method method){
		this.init();
		this.getCommonData();
		File file = new File(filePath);
		List<Element> elements = xp.getElements("data/" + method.getName());
		if(file.exists() && elements.size()>0){
			Object[][] object = new Object[elements.size()][];
			for(int i = 0 ;i<elements.size();i++){
				Map<String, String> commonData = this.getMergeData(xp.getChildrenNodesByElement(elements.get(i)), commonMap);
				Map<String, String> globalData = this.getMergeData(commonData, global);
				Object[] temp = new Object[]{globalData};
				object[i] = temp;
			}
			return object;
		}else{
			Object[][] object = new Object[1][1];
			object[0][0] = global;
			return object;
		}
	}
	/**
	 * 合并common节点
	 */
	private void getCommonData(){
		if(commonMap == null){
			Element element = xp.getElement("/*/common");
			commonMap = xp.getChildrenNodesByElement(element);
		}
	}
	
	/**
	 * 合并map ，判断map2中的key是否在map1中，如果不存在则将map2中的key 添加到map1 中，最后返回map1
	 * @param map1
	 * @param map2
	 * @return
	 */
	private Map<String, String> getMergeData(Map<String, String> map1 , Map<String, String> map2){
		Iterator<String> it = map2.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			String value = map2.get(key);
			if(!map1.containsKey(key)){
				map1.put(key, value);
			}
		}
		return map1;
	}
}
