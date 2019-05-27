package d3c0de.date;

import d3c0de.formatter.NumberFormatter;
import d3c0de.validate.Validate;
import java.time.LocalTime;

/**
 * Classe para controle e cálculos do horário.
 *
 * @see DCalendar, DHoliday, DDate, DWeekDay;
 * @version 1.0.0
 * @author deCOde < decospdl@gmail.com>
 */
public class Time {

    public static int NOW_HOUR = LocalTime.now().getHour();
    public static int NOW_MINUTE = LocalTime.now().getMinute();
    public static int NOW_SECOND = LocalTime.now().getSecond();
    private int hour;
    private int minute;
    private int second;

    /**
     * Construtor default inicia com horário do dia.
     */
    public Time() {
        this.hour = LocalTime.now().getHour();
        this.minute = LocalTime.now().getMinute();
        this.second = LocalTime.now().getSecond();
    }

    /**
     * Construor com passagem do tempo no formato de string. Devendo ter o
     * formato "HH:mm:ss", caso inválido irá conflitar com o array split string.
     *
     * @param time tempo no formato de string.
     */
    public Time(String time) {
        setTime(time);
    }

    /**
     * Defini um novo horário.Devendo ter o formato "HH:mm:ss", caso inválido
     * irá conflitar com o array split string.
     *
     * @param time tempo no formato de string.
     * @return o objeto atualizado com o novo valor.
     */
    public Time setTime(String time) {
        this.hour = LocalTime.parse(time).getHour();
        this.minute = LocalTime.parse(time).getMinute();
        this.second = LocalTime.parse(time).getSecond();
        return this;
    }

    /**
     * Retorna o horário no formato "HH:mm".
     *
     * @return o valor do horário do DTime.
     */
    public String getTime() {
        return NumberFormatter.lengthNumber(hour, 2) + ":" + NumberFormatter.lengthNumber(minute, 2)
                + ":" + NumberFormatter.lengthNumber(second, 2);
    }

    /**
     * O valor da data no format "HH:mm AM/PM".
     *
     * @return no formato string a data no formato específico.
     */
    public String getTimeAMPM() {
        if (hour > 12) {
            return NumberFormatter.lengthNumber(hour - 12, 2) + ":" + NumberFormatter.lengthNumber(minute, 2) + " PM";
        }
        return NumberFormatter.lengthNumber(hour, 2) + ":" + NumberFormatter.lengthNumber(minute, 2) + " AM";
    }

    /**
     * Retorna a hora no formto "HH".
     *
     * @return o valor da hora do DTtime.
     */
    public int getHour() {
        return hour;
    }

    /**
     * Retorna o minuto no formato "mm".
     *
     * @return o valor do minuto do DTime.
     */
    public int getMinute() {
        return minute;
    }

    /**
     * Retorna o segundo no formato "ss".
     *
     * @return o valor do segundo do DTime.
     */
    public int getSecond() {
        return second;
    }

    /**
     * Valida e set o valor do horário atribuido.
     *
     * @param hour hora no formato de int.
     * @return o objeto atualizado.
     */
    public Time setHour(int hour) {
        Validate.rangeOutside(hour, 0, 24);
        this.hour = hour;
        return this;
    }

    /**
     * Valida e set o valor do minuto atribuido.
     *
     * @param minute minuto no formato de int.
     * @return
     */
    public Time setMinute(int minute) {
        Validate.rangeOutside(minute, 0, 59);
        this.minute = minute;
        return this;
    }

    /**
     * Valida e set o valor do segundos atribuido.
     *
     * @param second segundos no formato de int.
     * @return
     */
    public Time setSecond(int second) {
        Validate.rangeOutside(second, 0, 59);
        this.second = second;
        return this;
    }

    /**
     * Converte a classe DTime em segundos.
     *
     * @return o valor em segundos.
     */
    public int convertSecond() {
        return LocalTime.of(hour, minute, second).toSecondOfDay();
    }

    /**
     * Converte a classe DTime em minutos.
     *
     * @return o valor em minutos.
     */
    public double convertMinute() {
        return (hour * 60) + minute + (((double) 1 / 60) * second);
    }

    /**
     * Converte a classe DTime em hora.
     *
     * @return o valor em horas.
     */
    public double convertHour() {
        return hour + (((double) 1 / 60) * minute) + (((double) 1 / 3600) * second);
    }

    /**
     * Transforma segundos em objeto DTime.
     *
     * @param second a quantidade segundos para criação do objeto.
     * @return o objeto comnforme os segundos passado por parametro.
     */
    public Time secondTo(int second) {
        setHour(second / 3600);
        setMinute((second - (hour * 3600)) / 60);
        setSecond((second - (hour * 3600 + minute * 60)) / 1);
        return this;
    }

    /**
     * Transforma minutos em objeto DTime.
     *
     * @param minute a quantidade minutos para criação do objeto.
     * @return o objeto comnforme os minutos passado por parametro.
     */
    public Time minuteTo(int minute) {
        setHour(minute / 60);
        setMinute((minute - (hour * 60)) / 1);
        return this;
    }

    /**
     * Transforma horas em objeto DTime.
     *
     * @param hour a quantidade horas para criação do objeto.
     * @return o objeto comnforme os horas passado por parametro.
     */
    public Time hourTo(int hour) {
        setHour(hour);
        return this;
    }

    /**
     * Soma dos objetos da classe DTime.
     *
     * @param time objeto DTime que será somado.
     * @return o DTime atualizado com a soma dos dois objetos.
     */
    public Time sum(Time time) {
        int newTime = time.convertSecond() + this.convertSecond();
        return secondTo(newTime);
    }

    /**
     * Subtrai dos objetos da classe DTime.
     *
     * @param time objeto DTime que será subtraido.
     * @return o DTime atualizado com a subtração dos dois objetos.
     */
    public Time subtract(Time time) {
        int newTime = time.convertSecond() - this.convertSecond();
        return secondTo(newTime);
    }

    /**
     * Multiplicação do objeto da classe DTime com um valor double.
     *
     * @param number objeto DTime que será multiplicado.
     * @return o DTime atualizado com a multiplicação do objeto com o double.
     */
    public Time multiply(double number) {
        int newTime = (int) (this.convertSecond() * number);
        return secondTo(newTime);
    }

    /**
     * Divisão do objeto da classe DTime com um valor double.
     *
     * @param number objeto DTime que será dividido.
     * @return o DTime atualizado com a divisão do objeto com o double.
     */
    public Time divide(int number) {
        int newTime = (int) (this.convertSecond() / number);
        return secondTo(newTime);
    }

    /**
     * Verifica se o objeto chamador é menor que o passodo por parâmetro.
     *
     * @param time o objeto DTime que será comparado.
     * @return true caso seja menor.
     */
    public boolean isLessThan(Time time) {
        return this.convertSecond() < time.convertSecond();
    }

    /**
     * Verifica se o objeto chamador é maior que o passodo por parâmetro.
     *
     * @param time o objeto DTime que será comparado.
     * @return true = caso seja maior.
     */
    public boolean isMoreThan(Time time) {
        return this.convertSecond() > time.convertSecond();
    }

    /**
     * Verifica se o objeto chamador é igual que o passodo por parâmetro.
     *
     * @param time o objeto DTime que será comparado.
     * @return true = caso seja iguais.
     */
    public boolean isEqualThan(Time time) {
        return this.convertSecond() == time.convertSecond();
    }

    /**
     * Adiciona a quantidade de segundos no valor existente no objeto DTime.
     *
     * @param second a quantidade de segundos adicionado.
     * @return o objeto DTime atualizado.
     */
    public Time addSecond(int second) {
        int newTime = this.convertSecond() + second;
        return secondTo(newTime);
    }

    /**
     * Adiciona a quantidade de minutos no valor existente no objeto DTime.
     *
     * @param minute a quantidade de minutos adicionado.
     * @return o objeto DTime atualizado.
     */
    public Time addMinute(int minute) {
        int newTime = this.convertSecond() + minuteTo(minute).convertSecond();
        return secondTo(newTime);
    }

    /**
     * Adiciona a quantidade de horas no valor existente no objeto DTime.
     *
     * @param hour a quantidade de horas adicionado.
     * @return o objeto DTime atualizado.
     */
    public Time addHour(int hour) {
        int newTime = this.convertSecond() + hourTo(hour).convertSecond();
        return secondTo(newTime);
    }
}
