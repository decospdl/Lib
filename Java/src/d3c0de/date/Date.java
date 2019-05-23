package date;

import formatter.DateFormatter;
import formatter.NumberFormatter;
import validate.Validate;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.GregorianCalendar;

/**
 * Classe para controle de Data, horário, feriado.
 *
 * @see DHoliday, DTime, DCalendar, DWeekDay.
 * @version 1.0.0
 * @author Andre
 */
public class Date extends Time {

    public static int NOW_DAY = DateFormatter.day(new GregorianCalendar(Calendar.TIME_ZONE, Calendar.LOCAL));
    public static int NOW_MONTH = DateFormatter.month(new GregorianCalendar(Calendar.TIME_ZONE, Calendar.LOCAL));
    public static int NOW_YEAR = DateFormatter.year(new GregorianCalendar(Calendar.TIME_ZONE, Calendar.LOCAL));
    private int day;
    private int month;
    private int year;

    /**
     * Construtor default inicia com a Data e horário do dia.
     */
    public Date() {
        super();
        this.day = DateFormatter.day(new GregorianCalendar(Calendar.TIME_ZONE, Calendar.LOCAL));
        this.month = DateFormatter.month(new GregorianCalendar(Calendar.TIME_ZONE, Calendar.LOCAL));
        this.year = DateFormatter.year(new GregorianCalendar(Calendar.TIME_ZONE, Calendar.LOCAL));
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
    public Time setDate(String date) {
        Validate.date(date);
        this.day = DateFormatter.day(date);
        this.month = DateFormatter.month(date);
        this.year = DateFormatter.year(date);
        return this;
    }

    /**
     * Retorna a data no formato "dd/MM/YYYY".
     *
     * @return o valor do horário do DDate.
     */
    public String getDate() {
        return getDay() + "/" + getMonth() + "/" + getYear();
    }

    /**
     * Retorna a data no formato "dd/MM/YYYY HH:mm:ss".
     *
     * @return o valor do horário do DDate.
     */
    public String getDateTime() {
        return getDate() + "   " + getTime();
    }

    /**
     * Retorna o dia no formto "dd".
     *
     * @return o valor do dia.
     */
    public String getDay() {
        return NumberFormatter.lengthNumber(day, 2);
    }

    /**
     * Retorna o mês no formato "MM".
     *
     * @return o valor do mês.
     */
    public String getMonth() {
        return NumberFormatter.lengthNumber(month, 2);
    }

    /**
     * Retorna o ano no formato "YYYY".
     *
     * @return o valor do ano.
     */
    public String getYear() {
        return NumberFormatter.lengthNumber(year, 4);
    }

    /**
     * Valida e set o valor do dia atribuido.
     *
     * @param day dia no formato de int.
     * @return o objeto atualizado.
     */
    public Date setDay(int day) {
        Validate.date(NumberFormatter.lengthNumber(day, 2) + "/" + getMonth() + "/" + getYear());
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
        Validate.date(getDay() + "/" + NumberFormatter.lengthNumber(month, 2) + "/" + getYear());
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
        Validate.date(getDay() + "/" + getMonth() + "/" + NumberFormatter.lengthNumber(year, 2));
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
        return DateFormatter.localDateTimeTo(LocalDateTime.of(year, month, day, Integer.parseInt(getHour()),
                Integer.parseInt(getMinute()), Integer.parseInt(getSecond())).plusDays(days));
    }

    public long subDates(Date date) {
        LocalDateTime thisDate = LocalDateTime.of(year, month, day, Integer.parseInt(getHour()),
                Integer.parseInt(getMinute()), Integer.parseInt(getSecond()));
        LocalDateTime otherDate = LocalDateTime.of(date.year, date.month, date.day, Integer.parseInt(date.getHour()),
                Integer.parseInt(date.getMinute()), Integer.parseInt(date.getSecond()));
        return ChronoUnit.DAYS.between(thisDate, otherDate);
    }

    public boolean isBeforeOrEqual(Date date) {
        LocalDateTime thisDate = LocalDateTime.of(year, month, day, Integer.parseInt(getHour()),
                Integer.parseInt(getMinute()), Integer.parseInt(getSecond()));
        LocalDateTime otherDate = LocalDateTime.of(date.year, date.month, date.day, Integer.parseInt(date.getHour()),
                Integer.parseInt(date.getMinute()), Integer.parseInt(date.getSecond()));
        return thisDate.isBefore(otherDate) || thisDate.isEqual(otherDate);
    }

    public boolean isAfterOrEqual(Date date) {
        LocalDateTime thisDate = LocalDateTime.of(year, month, day, Integer.parseInt(getHour()),
                Integer.parseInt(getMinute()), Integer.parseInt(getSecond()));
        LocalDateTime otherDate = LocalDateTime.of(date.year, date.month, date.day, Integer.parseInt(date.getHour()),
                Integer.parseInt(date.getMinute()), Integer.parseInt(date.getSecond()));
        return thisDate.isAfter(otherDate) || thisDate.isEqual(otherDate);
    }

    public void daysTo(Long days) {
        int dayOfYear = 308;
        Year y = Year.of(2018);
        LocalDate ld = y.atDay(days.intValue());
        System.out.println(ld);
    }

//    /**
//     * Converte a classe DTime em minutos.
//     *
//     * @return o valor em minutos.
//     */
//    public double convertMinute() {
//        return (hour * 60) + minute + (((double) 1 / 60) * second);
//    }
//
//    /**
//     * Converte a classe DTime em hora.
//     *
//     * @return o valor em horas.
//     */
//    public double convertHour() {
//        return hour + (((double) 1 / 60) * minute) + (((double) 1 / 3600) * second);
//    }
//
//    /**
//     * Transforma segundos em objeto DTime.
//     *
//     * @param second a quantidade segundos para criação do objeto.
//     * @return o objeto comnforme os segundos passado por parametro.
//     */
//    public DTime secondTo(int second) {
//        setHour(second / 3600);
//        setMinute((second - (hour * 3600)) / 60);
//        setSecond((second - (hour * 3600 + minute * 60)) / 1);
//        return this;
//    }
//
//    /**
//     * Transforma minutos em objeto DTime.
//     *
//     * @param minute a quantidade minutos para criação do objeto.
//     * @return o objeto comnforme os minutos passado por parametro.
//     */
//    public DTime minuteTo(int minute) {
//        setHour(minute / 60);
//        setMinute((minute - (hour * 60)) / 1);
//        return this;
//    }
//
//    /**
//     * Transforma horas em objeto DTime.
//     *
//     * @param hour a quantidade horas para criação do objeto.
//     * @return o objeto comnforme os horas passado por parametro.
//     */
//    public DTime hourTo(int hour) {
//        setHour(hour);
//        return this;
//    }
//
//    /**
//     * Soma dos objetos da classe DTime.
//     *
//     * @param time objeto DTime que será somado.
//     * @return o DTime atualizado com a soma dos dois objetos.
//     */
//    public DTime sum(DTime time) {
//        int newTime = time.convertSecond() + this.convertSecond();
//        return secondTo(newTime);
//    }
//
//    /**
//     * Subtrai dos objetos da classe DTime.
//     *
//     * @param time objeto DTime que será subtraido.
//     * @return o DTime atualizado com a subtração dos dois objetos.
//     */
//    public DTime subtract(DTime time) {
//        int newTime = time.convertSecond() - this.convertSecond();
//        return secondTo(newTime);
//    }
//
//    /**
//     * Multiplicação do objeto da classe DTime com um valor double.
//     *
//     * @param number objeto DTime que será multiplicado.
//     * @return o DTime atualizado com a multiplicação do objeto com o double.
//     */
//    public DTime multiply(double number) {
//        int newTime = (int) (this.convertSecond() * number);
//        return secondTo(newTime);
//    }
//
//    /**
//     * Divisão do objeto da classe DTime com um valor double.
//     *
//     * @param number objeto DTime que será dividido.
//     * @return o DTime atualizado com a divisão do objeto com o double.
//     */
//    public DTime divide(int number) {
//        int newTime = (int) (this.convertSecond() / number);
//        return secondTo(newTime);
//    }
//
//    /**
//     * O valor da data no format "HH:mm AM/PM".
//     *
//     * @return no formato string a data no formato específico.
//     */
//    public String getTimeAMPM() {
//        if (hour > 12) {
//            return Formatador.lengthNumber(hour - 12, 2) + ":" + minute() + " PM";
//        }
//        return hour() + ":" + minute() + " AM";
//    }
//
//    /**
//     * Verifica se o objeto chamador é menor que o passodo por parâmetro.
//     *
//     * @param time o objeto DTime que será comparado.
//     * @return true caso seja menor.
//     */
//    public boolean isLessThan(DTime time) {
//        return this.convertSecond() < time.convertSecond();
//    }
//
//    /**
//     * Verifica se o objeto chamador é maior que o passodo por parâmetro.
//     *
//     * @param time o objeto DTime que será comparado.
//     * @return true = caso seja maior.
//     */
//    public boolean isMoreThan(DTime time) {
//        return this.convertSecond() > time.convertSecond();
//    }
//
//    /**
//     * Verifica se o objeto chamador é igual que o passodo por parâmetro.
//     *
//     * @param time o objeto DTime que será comparado.
//     * @return true = caso seja iguais.
//     */
//    public boolean isEqualThan(DTime time) {
//        return this.convertSecond() == time.convertSecond();
//    }
//
//    /**
//     * Adiciona a quantidade de segundos no valor existente no objeto DTime.
//     *
//     * @param second a quantidade de segundos adicionado.
//     * @return o objeto DTime atualizado.
//     */
//    public DTime addSecond(int second) {
//        int newTime = this.convertSecond() + second;
//        return secondTo(newTime);
//    }
//
//    /**
//     * Adiciona a quantidade de minutos no valor existente no objeto DTime.
//     *
//     * @param minute a quantidade de minutos adicionado.
//     * @return o objeto DTime atualizado.
//     */
//    public DTime addMinute(int minute) {
//        int newTime = this.convertSecond() + minuteTo(minute).convertSecond();
//        return secondTo(newTime);
//    }
//
//    /**
//     * Adiciona a quantidade de horas no valor existente no objeto DTime.
//     *
//     * @param hour a quantidade de horas adicionado.
//     * @return o objeto DTime atualizado.
//     */
//    public DTime addHour(int hour) {
//        int newTime = this.convertSecond() + hourTo(hour).convertSecond();
//        return secondTo(newTime);
//    }
}
