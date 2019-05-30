package siet;

import d3c0de.database.ConfigMySql;
import d3c0de.database.ConnectionMySql;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andre
 */
public class DbGeralSentido {

    private static final String TABLE_NAME = "db_mun.geral_sentido";
    private static final String ID = "id_sentido";
    private static final String DESCRICAO = "descricao";
    private static final String APELIDO = "apelido";

    public static boolean sentidoExiste(String sentido) {
        boolean existe = false;
        try {
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE LOWER(" 
                    + DESCRICAO + ") = LOWER('" + sentido + "')";
            ResultSet rs = ConnectionMySql.selectFromDb(ConfigMySql.DB_MUN, query);
            if (rs.next()) {
                existe = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbEscEscala.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionMySql.closeConnection();
        }
        return existe;
    }
}
