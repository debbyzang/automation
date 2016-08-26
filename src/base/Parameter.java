package base;

import org.openqa.selenium.remote.DesiredCapabilities;

public class Parameter extends DesiredCapabilities {

	public String get(String name){
		Object cap = getCapability(name);	
		if(cap==null){
			return null;
		}else return cap.toString();	
	}
	
}
