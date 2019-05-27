package d3c0de.date;

/**
 * Classe para definição dos nomes do dia da semana.
 * @see DCalendar, DHoliday, DDate, DTime;
 * @version 1.0.0
 * @author Andre
 */
public enum Week {
    SUNDAY(new String [] {"Domingo", "Domingo", "DOM"}),
    MONDAY(new String [] {"Segunda", "Segunda-Feira", "SEG"}),
    TUESDAY(new String [] {"Terça", "Terça-Feira", "TER"}),
    WEDNESDAY(new String [] {"Quarta", "Quarta-Feira", "QUA"}),
    THURSDAY(new String [] {"Quinta", "Quinta-Feira", "QUI"}),
    FRIDAY(new String [] {"Sexta", "Sexta-Feira", "SEX"}),
    SATURDAY(new String [] {"Sábado", "Sábado", "SAB"});
    
    /**
     * Atributos estáticos para retorar o nome da semana.
     */
    public static int LONG_NAME = 0;
    public static int MEDIUM_NAME = 1;
    public static int SHORT_NAME = 2;
    
    /**
     * Array contendo os nomes da semana.
     */
    private String [] names;
    
    private Week(String [] names){
        this.names = names;
    }

    /**
     * Retorna o nome da semana conforme o index fornecido.
     * @param tipName o index do tipo do nome.
     * @return o nome conforme o index selecionado.
     */
    public String getName(int tipName) {
        return names[tipName];
    } 
}
