package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe para conexão no banco de Dados MySQL
 *
 * @author deCOde <decospdl@gmail.com>
 */
public abstract class ConnectionMySql {

    private static Connection con;
    private static PreparedStatement prstm;

    /**
     * Realiza a conexão com o database.
     *
     * @param database
     * @param query
     */
    public static void updateDb(ConfigMySql database, String query) {
        try {
            con = startConnection(database);
            prstm = con.prepareStatement(query);
            prstm.executeUpdate();
            prstm.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Inicializa a conexão com o banco de dados e retorna a conexão realizada.
     * @param database a configuração para acesso ao database.
     * @return a conexão realizada entre o banco.
     */
    public static Connection startConnection(ConfigMySql database) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(database.getUrl(),
                    database.getUsername(), database.getPassword());
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConnectionMySql.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
   
    /**
     * Encerra a conexão aberta com o banco de dados.
     */
    public static void closeConnection() {
        try {
            prstm.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Executa a query no pré statement e retorna o ResultSet da query.
     *
     * @param database
     * @param query a query que será executada no database.
     * @return os valores contido na query.
     */
    public static ResultSet selectFromDb(ConfigMySql database, String query) {
        try {
            con = startConnection(database);
            prstm = con.prepareStatement(query);
            return prstm.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionMySql.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
