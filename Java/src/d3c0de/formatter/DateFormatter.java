package d3c0de.formatter;

import d3c0de.date.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
            String newDate = String.format("%1$td/%1$tm/%1$tY", date);
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

    public static String adjustLenghtTime(String time) {
        String split[] = time.split(":");
        return NumberFormatter.lengthNumber(Integer.parseInt(split[0]), 2) + ":"
                + NumberFormatter.lengthNumber(Integer.parseInt(split[1]), 2) + ":"
                + NumberFormatter.lengthNumber(Integer.parseInt(split[2]), 2);
    }

    public static String adjustLenghtDate(String date) {
        String split[] = date.split("/");
        return NumberFormatter.lengthNumber(Integer.parseInt(split[2]), 4) + "-"
                + NumberFormatter.lengthNumber(Integer.parseInt(split[1]), 2) + "-"
                + NumberFormatter.lengthNumber(Integer.parseInt(split[0]), 2);
    }
}
