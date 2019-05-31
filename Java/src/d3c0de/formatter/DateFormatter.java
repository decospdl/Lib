package d3c0de.formatter;

import d3c0de.date.Date;
import static d3c0de.formatter.DateFormatter.toDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe para formatar classes derivado a data.
 *
 * @author d3c0de <decospdl@gmail.com>
 */
public abstract class DateFormatter {

    public static Date toDate(LocalDateTime date) {
        try {
            String newDate = String.format("%1$tY-%1$tm-%1$td", date);
            String newTime = String.format("%1$tH:%1$tM:%1$tS", date);
            return new Date(newDate, newTime);
        } catch (Exception ex) {
            Logger.getLogger(DateFormatter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        return LocalDateTime.of(date.getYear(), date.getMonth(), date.getDay(),
                date.getHour(), date.getMinute(), date.getSecond());
    }

//    public static Date toDate(String date) {        
//        if (date.length() > 10) {
//            date = date.substring(0, date.indexOf(" "));
//        }
//        if (date.contains("/")) {
//            return dateBrazilToDate(date);
//        } else {
//            return dateUsToDate(date);
//        }
//    }
    public static Date toDate(String date) {
        if (date.length() > 10) {
            String split[] = date.split(" ");
            if (date.contains("/")) {
                return dateBrazilToDate(split[0], split[1]);
            } else {
                return dateUsToDate(split[0], split[1]);
            }
        } else {
            if (date.contains("/")) {
                return dateBrazilToDate(date, "00:00:00");
            } else {
                return dateUsToDate(date, "00:00:00");
            }
        }
    }

    private static Date dateUsToDate(String date, String time) {
        int[] listDate = Converter.toIntList(date.split("-"));
        int[] listTime = Converter.toIntList(time.split(":"));
        return toDate(LocalDateTime.of(listDate[0], listDate[1], listDate[2],
                listTime[0], listTime[1], listTime[2]));
    }

    private static Date dateBrazilToDate(String date, String time) {
        int[] listDate = Converter.toIntList(date.split("/"));
        int[] listTime = Converter.toIntList(time.split(":"));
        return toDate(LocalDateTime.of(listDate[2], listDate[1], listDate[0],
                listTime[0], listTime[1], listTime[2]));
    }
}
