package d3c0de.database;

/**
 * Classe para execução de queries junto com a classe ConnectionMySql.
 * @author d3c0de <decospdl@gmail.com>
 */
public abstract class Query {

    /**
     * Query para seleção de dados no DB, valores null não serão printados.
     * @param columns as colunas que será buscada pelo ResultSet.
     * @param tableName o nome da tabela que será buscado.
     * @param condition a condição de busca para o retorno dos valores.
     * @param order a forma que será ordenado os dados.
     * @param groupBy a forma de agrupamento de dados.
     * @return a query conforme a passagem de parâmetro.
     */
    public static String select(String columns, String tableName, String condition, String order, String groupBy) {
        return "SELECT " + ((columns == null) ? "*" : columns) + " FROM " + tableName
                + ((condition == null) ? "" : " WHERE " + condition)
                + ((groupBy == null) ? "" : " GROUP BY " + groupBy)
                + ((order == null) ? "" : " ORDER BY " + order);               
    }
    
    /**
     * Query para atualização do banco de dados.
     * @param tableName o nome da tabela que será atualizada.
     * @param set a coluna e o valor que serão atualizados.
     * @param condition a condição para que executa  a atualização.
     * @return a qiery para atualizão do banco de dados.
     */
    public static String update(String tableName, String set, String condition){
        return "UPDATE "+ tableName +" SET "+ set + " WHERE "+ condition;
    }
    
    /**
     * Query para deletar o dado do database.
     * @param tableName o nome da tabela que será executado o delete.
     * @param condition a condição para que o dado venha ser deletado.
     * @return a query para execução do delete no database.
     */
    public static String delete(String tableName, String condition){
        return "DELETE FROM "+ tableName +" WHERE "+ condition;
    }
    
    /**
     * Query para inserção de dados no database.
     * @param tableName o nome da tabela que será inserido o dado.
     * @param columns o nome das colunas para inserção de dados na mesma sequencia que os valores.
     * @param values os valores que serão inseridos conforme a sequência das colunas.
     * @return a query para execução do inserir no database.
     */
    public static String insert(String tableName, String columns, String values){
        return "INSERT INTO "+ tableName +" ("+ columns +") VALUES ("+ values +")";
    }  
}
