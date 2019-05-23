package d3c0de.formatter;

/**
 * Classe para formatação de número e seus derivados.
 *
 * @author Andre
 */
public abstract class NumberFormatter {

    /**
     * Acrescenta zeros conforme ao tamanho especificado length.
     *
     * @param number o numero que será formatado.
     * @param length o tamanho do número.
     * @return o número ajustado com a quantidade de zeros.
     */
    public static String lengthNumber(int number, int length) {
        String value = String.valueOf(number);
        for (int i = value.length(); i < length; i++) {
            value = 0 + value;
        }
        return value;
    }
}
