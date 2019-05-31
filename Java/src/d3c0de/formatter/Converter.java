package d3c0de.formatter;

/**
 * Convertor de varias classes e utilidades.
 *
 * @author d3c0de <decospdl@gmail.com>
 */
public abstract class Converter {

    /**
     * Converte uma lista de String e uma lista de Int, pode haver má conversão
     * caso não tenha uma String no formato de número.
     *
     * @param listString lista de String no formato de número.
     * @return a lista de int conforme a conversão dos itens.
     */
    public static int[] toIntList(String[] listString) {
        int[] listInt = new int[listString.length];
        for (int i = 0; i < listString.length; i++) {
            listInt[i] = Integer.parseInt(listString[i]);
        }
        return listInt;
    }
}
