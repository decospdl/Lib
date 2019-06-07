package d3c0de.date;

import d3c0de.formatter.Converter;
import d3c0de.formatter.DateFormatter;
import d3c0de.validate.Validate;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

/**
 * Classe para controle de Data, horário, feriado.
 *
 * @see DHoliday, DTime, DCalendar, DWeekDay.
 * @version 1.0.0
 * @author d3c0de <decospdl@gmail.com>
 */
public class Date implements Comparable<Date> {

    private int day;
    private int month;
    private int year;
    private int format;
    private Time time;

    /**
     * Construtor default inicia com a Data e horário do dia.
     */
    public Date() {
        this.time = new Time();
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
     */
    public Date(String date) {
        setDate(date);
    }

    /**
     * Defini uma nova data. Devendo ter o formato "YYYY-MM-DD", caso inválido
     * irá conflitar com o parsing.
     *
     * @param date data no formato de string.
     * @return o objeto atualizado com o novo valor.
     */
    public Date setDate(String date) {
        if (date.length() > 10) {
            String split[] = date.split(" ");
            if (date.contains("/")) {
                dateBrazilToDate(split[0], split[1]);
            } else {
                dateUsToDate(split[0], split[1]);
            }
        } else {
            if (date.contains("/")) {
                dateBrazilToDate(date, "00:00:00");
            } else {
                dateUsToDate(date, "00:00:00");
            }
        }
        return this;
    }

    /**
     * Atribui o valores para cada atributo do objeto no formato de data Brasil.
     *
     * @param date Exemplo: "DD/MM/YYYY"
     * @param time Exemplo: "HH:MM:SS"
     */
    private void dateUsToDate(String date, String time) {
        int[] listDate = Converter.toIntList(date.split("-"));
        this.year = listDate[0];
        this.month = listDate[1];
        this.day = listDate[2];
        this.time = new Time(time);
    }

    /**
     * Atribui o valores para cada atributo do objeto no formato de data US.
     *
     * @param date Exemplo: "YYYY-MM-DD"
     * @param time Exemplo: "HH:MM:SS"
     */
    private void dateBrazilToDate(String date, String time) {
        int[] listDate = Converter.toIntList(date.split("/"));
        this.year = listDate[2];
        this.month = listDate[1];
        this.day = listDate[0];
        this.time = new Time(time);
    }

    public static int BRL = 0;
    public static int BRL_TIME = 1;
    public static int BRL_TIME_SECONDS = 2;
    public static int BRL_TIME_AMPM = 3;
    public static int US = 4;
    public static int US_TIME = 5;
    public static int US_TIME_SECONDS = 6;
    public static int US_TIME_AMPM = 7;

    public Date setFormat(int format) {
        Validate.rangeBetween(format, 0, 7);
        this.format = format;
        return this;
    }

    /**
     * Retorna a data no formato "dd/MM/YYYY".
     *
     * @param format BRL = DD/MM/YYYY BRL_TIME = DD/MM/YYYY hh:mm
     * BRL_TIME_SECONDS = DD/MM/YYYY hh:mm:ss BRL_TIME_AMPM = DD/MM/YYYY US
     * US_TIME US_TIME_SECONDS US_TIME_AMPM
     * @return o valor do horário do DDate.
     */
    public String getDate(int format) {
        this.format = format;
        String[] dateFormat = new String[]{
            String.format("%1$02d/%2$02d/%3$04d", day, month, year),
            String.format("%1$02d/%2$02d/%3$04d ", day, month, year) + time.getTime(Time.SIMPLE),
            String.format("%1$02d/%2$02d/%3$04d ", day, month, year) + time.getTime(Time.SECONDS),
            String.format("%1$02d/%2$02d/%3$04d ", day, month, year) + time.getTime(Time.AMPM),
            String.format("%3$04d-%2$02d-%1$02d ", day, month, year),
            String.format("%3$04d-%2$02d-%1$02d ", day, month, year) + time.getTime(Time.SIMPLE),
            String.format("%3$04d-%2$02d-%1$02d ", day, month, year) + time.getTime(Time.SECONDS),
            String.format("%3$04d-%2$02d-%1$02d ", day, month, year) + time.getTime(Time.AMPM)};
        return dateFormat[format];
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
     * Retorna o objeto contida no objeto Date.
     * 
     * @return {@link Time Time.class}
     */
    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
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
                time.getHour(), time.getMinute(), time.getSecond()).plusDays(days));
    }

    /**
     * Subtrai duas datas retornando a quantidade de dias de diferença entre.
     *
     * @param date a data que será compara para o cálculo.
     * @return a quantidade de dias de diferença entre
     */
    public long subDates(Date date) {
        LocalDateTime thisDate = DateFormatter.toLocalDateTime(this);
        LocalDateTime otherDate = DateFormatter.toLocalDateTime(date);
        return ChronoUnit.DAYS.between(thisDate, otherDate);
    }

    /**
     * O nome específico do mês.
     *
     * @param typeName se é extemso ou abreviado;
     * @return a descrição do mês.
     */
    public String getNameMonth(int typeName) {
        return d3c0de.date.Month.getName(month, typeName);
    }

    /**
     * O nome específico do mês.
     *
     * @param typeName se é extemso ou abreviado;
     * @return a descrição do mês.
     */
    public String getWeekDay(int typeName) {
        LocalDateTime date = DateFormatter.toLocalDateTime(this);
        return Week.getName(date.getDayOfWeek().getValue(), typeName);
    }

    /**
     * Compara o ano da data.
     *
     * @param date a {@link Data Data.class} que será comparada
     * @return 1 = maior | -1 = menor | 0 = igual
     */
    public int compareYear(Date date) {
        if (year > date.getYear()) {
            return 1;
        } else if (year < date.getYear()) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * Compara o mês da data.
     *
     * @param date a {@link Data Data.class} que será comparada
     * @return 1 = maior | -1 = menor | 0 = igual
     */
    public int compareMonth(Date date) {
        if (month > date.getMonth()) {
            return 1;
        } else if (month < date.getMonth()) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * Compara o dia da data.
     *
     * @param date a {@link Data Data.class} que será comparada
     * @return 1 = maior | -1 = menor | 0 = igual
     */
    public int compareDay(Date date) {
        if (day > date.getDay()) {
            return 1;
        } else if (day < date.getDay()) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public int compareTo(Date date) {
        int yearAux = compareYear(date);
        if (yearAux == 0) {
            int monthAux = compareMonth(date);
            if (monthAux == 0) {
                int dayAux = compareDay(date);
                if (dayAux == 0) {
                    return time.compareTo(date.getTime());
                }
            }
            return monthAux;
        }
        return yearAux;
    }

    @Override
    public String toString() {
        return getDate(format);
    }
}
