package d3c0de.formatter;

import d3c0de.date.Date;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe para formatar classes derivado a data.
 *
 * @author d3c0de <decospdl@gmail.com>
 */
public abstract class DateFormatter {

    /**
     * Converte objeto da classe LocalDateTime para Date.
     *
     * @param date Exemplo: Qialquer objeto
     * {@link LocalDateTime LocalDateTime.class}
     * @return {@link Date Date.class}
     */
    public static Date toDate(LocalDateTime date) {
        try {
            String newDate = String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS", date);
            return new Date(newDate);
        } catch (Exception ex) {
            Logger.getLogger(DateFormatter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Converte objeto da classe Date para LocalDateTime.
     *
     * @param date Exemplo: Qialquer objeto {@link Date Date.class}
     *
     * @return {@link LocalDateTime LocalDateTime.class}
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        return LocalDateTime.of(date.getYear(), date.getMonth(), date.getDay(),
                date.getTime().getHour(), date.getTime().getMinute(), date.getTime().getSecond());
    }
}
