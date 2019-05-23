package d3c0de.date;

/**
 *
 * @author Andre
 */
public enum MonthYear {
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

    private MonthYear(String[] names) {
        this.names = names;
    }

    /**
     * Retorna o nome do mês conforme o index fornecido.
     *
     * @param tipName o index do tipo do nome.
     * @return o nome conforme o index selecionado.
     */
    public String getName(int tipName) {
        return names[tipName];
    }
}
