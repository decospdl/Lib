package d3c0de.date;

import d3c0de.formatter.DateFormatter;
import d3c0de.formatter.NumberFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

/**
 * Classe para controle de Data, horário, feriado.
 *
 * @see DHoliday, DTime, DCalendar, DWeekDay.
 * @version 1.0.0
 * @author Andre
 */
public class Date extends Time {

    public static int NOW_DAY = LocalDate.now().getDayOfMonth();
    public static int NOW_MONTH = LocalDate.now().getMonth().getValue();
    public static int NOW_YEAR = LocalDate.now().getYear();
    private int day;
    private int month;
    private int year;

    /**
     * Construtor default inicia com a Data e horário do dia.
     */
    public Date() {
        super();
        this.day = LocalDate.now().getDayOfMonth();
        this.month = LocalDate.now().getMonth().getValue();
        this.year = LocalDate.now().getYear();
    }

    /**
     * Construor com passagem de parâmetro da data e do tempo no formato de
     * string.Devendo ter o formato data "DD/MM/YYYY" e horário "HH:mm:ss", caso
     * inválido irá conflitar com o array split string.
     *
     * @param date data no formato "DD/MM/YYYY".
     * @param time hora no formato "HH:mm:ss".
     */
    public Date(String date, String time) {
        super(time);
        setDate(date);
    }

    /**
     * Defini uma nova data. Devendo ter o formato "dd/MM/YYYY", caso inválido
     * irá conflitar com o parsing.
     *
     * @param date data no formato de string.
     * @return o objeto atualizado com o novo valor.
     */
    public Date setDate(String date) {
        this.day = LocalDate.parse(date).getDayOfMonth();
        this.month = LocalDate.parse(date).getMonth().getValue();
        this.year = LocalDate.parse(date).getYear();
        return this;
    }

    /**
     * Retorna a data no formato "dd/MM/YYYY".
     *
     * @return o valor do horário do DDate.
     */
    public String getDate() {
        return NumberFormatter.lengthNumber(day, 2) + "/" + NumberFormatter.lengthNumber(month, 2) 
                +"/" + NumberFormatter.lengthNumber(year, 4);
    }

    /**
     * Retorna a data no formato "dd/MM/YYYY HH:mm:ss".
     *
     * @return o valor do horário do DDate.
     */
    public String getDateTime() {
        return getDate() + "   " + getTime();
    }

    public String getDbDate() {
        return NumberFormatter.lengthNumber(year, 4) + "-" + NumberFormatter.lengthNumber(month, 2) 
                +"-" + NumberFormatter.lengthNumber(day, 2);
    }

    public String getDbDateTime() {
        return getDbDate() + " " + getTime();
    }

    /**
     * Retorna o dia no formto "dd".
     *
     * @return o valor do dia.
     */
    public int getDay() {
        return day;
    }

    /**
     * Retorna o mês no formato "MM".
     *
     * @return o valor do mês.
     */
    public int getMonth() {
        return month;
    }

    /**
     * Retorna o ano no formato "YYYY".
     *
     * @return o valor do ano.
     */
    public int getYear() {
        return year;
    }

    /**
     * Valida e set o valor do dia atribuido.
     *
     * @param day dia no formato de int.
     * @return o objeto atualizado.
     */
    public Date setDay(int day) {
        LocalDate.of(year, Month.of(month), day);
        this.day = day;
        return this;
    }

    /**
     * Valida e set o valor do mes atribuido.
     *
     * @param month mes no formato de int.
     * @return o objeto atualizado.
     */
    public Date setMonth(int month) {
        LocalDate.of(year, Month.of(month), day);
        this.month = month;
        return this;
    }

    /**
     * Valida e set o valor do ano atribuido.
     *
     * @param year ano no formato de int.
     * @return o objeto atualizado.
     */
    public Date setYear(int year) {
        LocalDate.of(year, Month.of(month), day);
        this.year = year;
        return this;
    }

    /**
     * Converte a classe DTime em segundos.
     *
     * @param days
     * @return o valor em segundos.
     */
    public Date addDay(int days) {
        return DateFormatter.toDate(LocalDateTime.of(year, month, day,
                getHour(), getMinute(), getSecond()).plusDays(days));
    }

    public long subDates(Date date) {
        LocalDateTime thisDate = LocalDateTime.of(year, month, day,
                getHour(), getMinute(), getSecond());
        LocalDateTime otherDate = LocalDateTime.of(date.year, date.month, date.day,
                date.getHour(), date.getMinute(), date.getSecond());
        return ChronoUnit.DAYS.between(thisDate, otherDate);
    }

    public boolean isBeforeOrEqual(Date date) {
        LocalDateTime thisDate = LocalDateTime.of(year, month, day,
                getHour(), getMinute(), getSecond());
        LocalDateTime otherDate = LocalDateTime.of(date.year, date.month, date.day,
                date.getHour(), date.getMinute(), date.getSecond());
        return thisDate.isBefore(otherDate) || thisDate.isEqual(otherDate);
    }

    public boolean isAfterOrEqual(Date date) {
        LocalDateTime thisDate = LocalDateTime.of(year, month, day,
                getHour(), getMinute(), getSecond());
        LocalDateTime otherDate = LocalDateTime.of(date.year, date.month, date.day,
                date.getHour(), date.getMinute(), date.getSecond());
        return thisDate.isAfter(otherDate) || thisDate.isEqual(otherDate);
    }

    public String getNameMonth(int typeName) {
        return d3c0de.date.Month.getName(month, typeName);
    }
}
