package d3c0de.validate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Classe para validação de dados em RunTime.
 *
 * @version 1.0.0
 * @author d3c0de <decospdl@gmail.com>
 */
public abstract class Validate {

    /**
     * Lança um throw em runtime caso o valor esteja dentro range.
     *
     * @param value valor que será testado.
     * @param begin valor de inicio do range.
     * @param end valor do fim do range.
     */
    public static void rangeBetween(int value, int begin, int end) {
        if (value > begin || value < end) {
            throw new RuntimeException("O valor " + value + " deve ser menor do que "
                    + begin + " ou maior do que " + end);
        }
    }

    /**
     * Lança um throw em runtime caso o valor esteja fora do range.
     *
     * @param value valor que será testado.
     * @param begin valor de inicio do range.
     * @param end valor do fim do range.
     */
    public static void rangeOutside(int value, int begin, int end) {
        if (value < begin || value > end) {
            throw new RuntimeException("O valor " + value + " deve ser maior do que "
                    + begin + " e menor do que " + end);
        }
    }

    /**
     * Lança um throw em runtime caso o valor sjea menor do que critério.
     *
     * @param value valor que será testado.
     * @param crit o critério de limite.
     */
    public static void lessThan(int value, int crit) {
        if (value < crit) {
            throw new RuntimeException("O valor " + value + " deve ser maior do que " + crit);
        }
    }

    /**
     * Lança um throw em runtime caso o valor seja maior do que critério.
     *
     * @param value valor que será testado.
     * @param crit o critério de limite.
     */
    public static void moreThan(int value, int crit) {
        if (value > crit) {
            throw new RuntimeException("O valor " + value + " deve ser menor do que " + crit);
        }
    }

    /**
     * Lança um throw em runtime caso o valor seja igual critério.
     *
     * @param value valor que será testado.
     * @param crit o critério de limite.
     */
    public static void equalThan(int value, int crit) {
        if (value == crit) {
            throw new RuntimeException("O valor " + value + " deve ser diferente do que " + crit);
        }
    }

    /**
     * Lança um throw em runtime caso a data não exista.
     * @param date o formato da data a ser verificada.
     */
    public static void date(String date) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.setLenient(false);
            dateFormat.parse(date);
        } catch (Exception ex) {
            throw new RuntimeException("O valor da data " + date + " é invalido!");
        }
    }
}
