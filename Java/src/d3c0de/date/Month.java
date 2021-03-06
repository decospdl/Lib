package d3c0de.date;

/**
 * Enum com o nome de todos os meses na lingua portugesa.
 * @author d3c0de <decospdl@gmail.com>
 */
public enum Month {
    JANUARY(new String[]{"Janeiro", "JAN"}),
    FREBRUARY(new String[]{"Fevereiro", "FEV"}),
    MARCH(new String[]{"Março", "MAR"}),
    APRIL(new String[]{"Abril", "ABR"}),
    MAY(new String[]{"Maio", "MAI"}),
    JUNE(new String[]{"Junho", "JUN"}),
    JULY(new String[]{"Julho", "JUL"}),
    AUGUST(new String[]{"Agosto", "AGO"}),
    SEPTEMBER(new String[]{"Setembro", "SET"}),
    OUCTOBER(new String[]{"Outubro", "OUT"}),
    NOVEMBER(new String[]{"Novembro", "NOV"}),
    DECEMBER(new String[]{"Dezembro", "DEZ"});

    /**
     * Atributos estáticos para retorar o nome do mês.
     */
    public static int LONG_NAME = 0;
    public static int SHORT_NAME = 1;

    /**
     * Array contendo os nomes dos meses.
     */
    private String[] names;

    private Month(String[] names) {
        this.names = names;
    }

    /**
     * Retorna o nome do mês conforme o index fornecido.
     *
     * @param month
     * @param tipName o index do tipo do nome.
     * @return o nome conforme o index selecionado.
     */
    public static String getName(int month,int tipName) {
        return Month.values()[month - 1].names[tipName];
    }
}
