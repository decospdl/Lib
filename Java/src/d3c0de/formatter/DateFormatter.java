package d3c0de.formatter;

import d3c0de.date.Date;
import java.time.LocalDateTime;

/**
 * Classe para formatar classes derivado a data.
 *
 * @author d3c0de <decospdl@gmail.com>
 */
public abstract class DateFormatter {
  
    public static Date toDate(LocalDateTime date){
        String newDate = date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear();
        String newTime = date.getHour()+ ":" + date.getMinute() + ":" + date.getSecond();
        return new Date(newDate, newTime);    
    }
}
