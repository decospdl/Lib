package d3c0de.formatter;

/**
 * Classe para manusear string, padronizar texto, entre outros.
 *
 * @author d3c0de <decospdl@gmail.com>
 * @version 1.0.0
 */
public abstract class StringFormatter {

    /**
     * Preenche o lado esquerdo com espaços, padronizar tamanho.
     *
     * @param word Exemplo: "Texto qualquer"
     * @param length Exemplo: "2,5,6..." quantidade de espaços
     * @return a word ajustada com o preenchimento passado por parâmetro.
     */
    public static String leftPad(String word, int length) {
        return String.format("%" + length + "." + word.length() + "s", word);
    }

    /**
     * Preenche o lado esquerdo com zeros, padronizar tamanho.
     *
     * @param number Exemplo: "1,2,3..." qualque número
     * @param length Exemplo: "2,5,6..." quantidade de espaços
     * @return a word ajustada com o preenchimento passado por parâmetro.
     */
    public static String padZero(int number, int length) {
        return String.format("%0" + length + "d", number);
    }

    /**
     * Preenche o lado direito com espaços, padronizar tamanho.
     *
     * @param word Exemplo: "Texto qualquer"
     * @param length Exemplo: "2,5,6..." quantidade de espaços
     * @return a word ajustada com o preenchimento passado por parâmetro.
     */
    public static String rightPad(String word, int length) {
        return String.format("%-" + length + "." + word.length() + "s", word);
    }

    /**
     * Preenche o lado esquerdo e direito com espaços, padronizar tamanho.
     *
     * @param word Exemplo: "Texto qualquer"
     * @param length Exemplo: "2,5,6..." quantidade de espaços
     * @return a word ajustada com o preenchimento passado por parâmetro.
     */
    public static String centerPad(String word, int length) {
        int halfLength = word.length() / 2;
        int newLength = (word.length() % 2 == 0) ? (word.length() + 1) : word.length();
        return String.format("%" + (length - halfLength)
                + "s%" + newLength + "s%-" + (length - halfLength) + "s", "", word, "");
    }

    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int CENTER = 3;

    /**
     * Preenche o texto conforme o tipo passado por parâmetro.
     *
     * @param pad Exemplos: LEFT = 1; RIGHT = 2; CENTER = 3;
     * @param value Exemplo: "Qualquer texto"
     * @param length Exemplo: {1,2,3,4 ...} tamanho do preemchimento.
     * @return o texto com o preenchimento passado por parâmetro.
     */
    private static String padTo(int pad, String value, int length) {
        String padValue;
        switch (pad) {
            case 1:
                padValue = leftPad(value, length);
                break;
            case 2:
                padValue = rightPad(value, length);
                break;
            case 3:
                padValue = centerPad(value, length);
                break;
            default:
                padValue = null;
        }
        return padValue;
    }

    /**
     * Cria uma linha "_" conforme o tamanho passado por parâmetro.
     * @param size Exemplo: {1,2,3,...}
     * @return a linha conforme o tamanho passado por parâmetro.
     */
    public static String createLine(int size) {
        String line = "";
        for (int i = 0; i < size; i++) {
            line += "-";
        }
        return line;
    }

    /**
     * Cria tabela de texto confome a matrix passada por parâmetro.
     * @param values matrix de String Exemplo: {{"t1","t2"},{t3,t4}}
     * @param length a quantidade de preenchimento que o texto irá possuir. Exemplo: {1,2,3,4 ...}
     * @param padTo Exemplos: LEFT = 1; RIGHT = 2; CENTER = 3;
     * @return o texto com o preenchimento passado por parâmetro.
     */
    public static String createTable(String[][] values, int length, int padTo) {
        String aux = "", table = "";
        for (String[] list : values) {
            aux = "";
            for (String value : list) {
                aux += padTo(padTo, value, length);
            }
            table += createLine(aux.length() - 2) + "\n";
            table += aux + "\n";
        }
        table += createLine(aux.length() - 2) + "\n";
        return table.replace("||", "|");
    }
}
