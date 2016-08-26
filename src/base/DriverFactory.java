package base;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
//import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class DriverFactory {
	
	private DriverFactory(){}
	private static WebDriver driver = null ;
	public static  WebDriver getDriver(String type){
		if(driver == null){
			return  createDriver(type);
		}
		return driver;
	}
//	public static  WebDriver getDriver(String type){
//		return driver = createDriver(type);
//	}
//	
	private static WebDriver createDriver(String type){
		switch(type.toLowerCase()){
		case "firefox":
			driver = createFirefoxDriver();
			break;
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "source/chromedriver.exe");
			driver = createChromeDriver();
			break;
		case "ie":
			System.setProperty("webdriver.ie.driver", "source/IEDriverServer.exe");
			driver = createIEDriver();
			break;
		default:
			System.out.println("Error:Invalid Brower Type!!");
			break;
		}
		return driver ;
	}
	
	private static WebDriver createFirefoxDriver(){
		System.setProperty("webdriver.firefox.bin", "D://Program Files/firefox/firefox.exe");
//		File file = new File("files/fko3r6ky.默认用户");
//		FirefoxProfile profile = new FirefoxProfile(file);
		FirefoxProfile profile = new FirefoxProfile();
		try {
			profile.addExtension(new File("source/firebug-2.0.17.xpi"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		profile.setPreference("extensions.firebug.allPagesActivation", "on");
//		profile.setPreference("extensions.firebug.defaultPanelName", "net");
		profile.setEnableNativeEvents(false);
		return new FirefoxDriver(profile);
	}
	
	private static WebDriver createChromeDriver(){
		System.setProperty("webdriver.chrome.driver", "source/chromedriver.exe");
	//	File file = new File ("--user-data-dir=C://Users/Administrator/AppData/Roaming/Google/Chrome/User Data");
		ChromeOptions option = new ChromeOptions();
//		option.addArguments("--user-data-dir=C:/Users/Administrator/AppData/Local/115Chrome/User Data");	//加载用户配置
	//	 options.addExtensions(file);
		return new ChromeDriver(option);
	}
	
	private static WebDriver createIEDriver(){
		return new InternetExplorerDriver();
	}
}
