package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHandler {
	
	public static String getTimestamp(){
		return getFormatDate(new Date(), "yyyyMMddHHmmssSSS");
	}

	private static String getFormatDate(Date date , String reg){
		SimpleDateFormat dateFormat = new SimpleDateFormat(reg);
		return dateFormat.format(date);
	}
}
