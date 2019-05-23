package d3c0de.date;

import java.util.Locale;
import java.util.TimeZone;

/**
 *
 * @author Andre
 */
public class Calendar {
    public static TimeZone TIME_ZONE = TimeZone.getTimeZone("GMT-03:00");
    public static Locale LOCAL = new Locale("pt", "BR"); 
    public static java.util.Calendar CALENDAR = java.util.Calendar.getInstance(TIME_ZONE, LOCAL);

    public Calendar(){
        
    }
}
