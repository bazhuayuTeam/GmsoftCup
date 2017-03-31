package time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChangeTime {
	public static long changeToMills(String time){
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Date date;
		try {
			date = format.parse(time);
			 return date.getTime();
		} catch (ParseException e) {
			return 0;
		}
	}
}
