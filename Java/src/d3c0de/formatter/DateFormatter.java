package d3c0de.formatter;

import d3c0de.date.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.GregorianCalendar;

/**
 * Classe para formatar classes derivado a data.
 *
 * @author d3c0de <decospdl@gmail.com>
 */
public abstract class DateFormatter {

    /**
     * Converte um texto HH:mm:ss em somente HH.
     *
     * @param hour o valor hora em string.
     * @return retorna o vaor hora inteiro.
     */
    public static int hour(String hour) {
        String[] time = hour.split(":");
        return Integer.parseInt(time[0]);
    }

    //Teste
    /**
     * Converte um texto HH:mm:ss em somente mm.
     *
     * @param minute o valor minuto em string.
     * @return retorna o vaor minuto inteiro.
     */
    public static int minute(String minute) {
        String[] time = minute.split(":");
        return Integer.parseInt(time[1]);
    }

    /**
     * Converte um texto HH:mm:ss em somente ss.
     *
     * @param second o valor segundo em string.
     * @return retorna o vaor segundo inteiro.
     */
    public static int second(String second) {
        String[] time = second.split(":");
        return Integer.parseInt(time[2]);
    }

    /**
     * Converte um texto DD/MM/yyyy em somente DD.
     *
     * @param day o valor dia em string.
     * @return retorna o vaor dia inteiro.
     */
    public static int day(String day) {
        String[] date = day.split("/");
        return Integer.parseInt(date[0]);
    }

    /**
     * Converte um texto DD/MM/yyyy em somente mm.
     *
     * @param month o valor mês em string.
     * @return retorna o vaor mês inteiro.
     */
    public static int month(String month) {
        String[] date = month.split("/");
        return Integer.parseInt(date[1]);
    }

    /**
     * Converte um texto DD/MM/yyyy em somente yyyy.
     *
     * @param year o valor ano em string.
     * @return retorna o vaor ano inteiro.
     */
    public static int year(String year) {
        String[] date = year.split("/");
        return Integer.parseInt(date[2]);
    }

    /**
     * Converte uma data GregorianCalendar HH:mm:ss em somente HH.
     *
     * @param calendar a data para ser retirado somente a hora.
     * @return retorna o valor hora inteiro.
     */
    public static int hour(GregorianCalendar calendar) {
        SimpleDateFormat fmt = new SimpleDateFormat("HH");
        fmt.setCalendar(calendar);
        return Integer.parseInt(fmt.format(calendar.getTime()));
    }

    /**
     * Converte uma data GregorianCalendar HH:mm:ss em somente mm.
     *
     * @param calendar a data para ser retirado somente o minuto.
     * @return retorna o valor minuto inteiro.
     */
    public static int minute(GregorianCalendar calendar) {
        SimpleDateFormat fmt = new SimpleDateFormat("mm");
        fmt.setCalendar(calendar);
        return Integer.parseInt(fmt.format(calendar.getTime()));
    }

    /**
     * Converte uma data GregorianCalendar HH:mm:ss em somente ss.
     *
     * @param calendar a data para ser retirado somente o segundo.
     * @return retorna o valor segundo inteiro.
     */
    public static int second(GregorianCalendar calendar) {
        SimpleDateFormat fmt = new SimpleDateFormat("ss");
        fmt.setCalendar(calendar);
        return Integer.parseInt(fmt.format(calendar.getTime()));
    }

    /**
     * Converte uma data GregorianCalendar DD/MM/yyyy em somente DD.
     *
     * @param calendar a data para ser retirado somente o dia.
     * @return retorna o valor dia inteiro.
     */
    public static int day(GregorianCalendar calendar) {
        SimpleDateFormat fmt = new SimpleDateFormat("dd");
        fmt.setCalendar(calendar);
        return Integer.parseInt(fmt.format(calendar.getTime()));
    }

    /**
     * Converte uma data GregorianCalendar DD/MM/yyyy em somente mm.
     *
     * @param calendar a data para ser retirado somente o mês.
     * @return retorna o valor mês inteiro.
     */
    public static int month(GregorianCalendar calendar) {
        SimpleDateFormat fmt = new SimpleDateFormat("MM");
        fmt.setCalendar(calendar);
        return Integer.parseInt(fmt.format(calendar.getTime()));
    }

    /**
     * Converte uma data GregorianCalendar DD/MM/yyyy em somente yyyy.
     *
     * @param calendar a data para ser retirado somente o ano.
     * @return retorna o valor ano inteiro.
     */
    public static int year(GregorianCalendar calendar) {
        SimpleDateFormat fmt = new SimpleDateFormat("YYYY");
        fmt.setCalendar(calendar);
        return Integer.parseInt(fmt.format(calendar.getTime()));
    }
    
    public static Date localDateTimeTo(LocalDateTime date){
        String newDate = date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear();
        String newTime = date.getHour()+ ":" + date.getMinute() + ":" + date.getSecond();
        return new Date(newDate, newTime);
        
    }
}
