package base;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class Log {

	static final Logger logger = LogManager.getLogger(Log.class.getName());
	
	static{
		DOMConfigurator.configure("log4j.xml");
		BasicConfigurator.configure();
	}
	
	public synchronized static void debug(String msg){
		logger.log(Log.class.getName(), Level.DEBUG, msg, null);
	}
	
	public synchronized static void info(String msg){
		logger.log(Log.class.getName(), Level.INFO, msg, null);
	}
	
	public synchronized static void warn(String msg){
		logger.log(Log.class.getName(), Level.WARN, msg, null);
	}
	
	public synchronized static void error(String msg){
		logger.log(Log.class.getName(), Level.ERROR, msg, null);
	}
}
