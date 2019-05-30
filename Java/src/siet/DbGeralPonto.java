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
public class DbGeralPonto {

    public static final String TABLE_NAME = "db_mun.geral_ponto";
    public static final String ID = "id";
    public static final String DESCRICAO = "descricao";
    public static final String LOCAL_SAIDA = "local_saida";

    public static boolean pontoExiste(String sentido) {
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
        } finally {
            ConnectionMySql.closeConnection();
        }
        return existe;
    }

    public static String getIdByDescricao(String descricao) {
        String id = null;
        try {
            String query = "SELECT "+ ID +" FROM " + TABLE_NAME + " WHERE LOWER("
                    + DESCRICAO + ") = LOWER('" + descricao + "')";
            ResultSet rs = ConnectionMySql.selectFromDb(ConfigMySql.DB_MUN, query);
            if (rs.next()) {
                id = rs.getString(ID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbEscEscala.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionMySql.closeConnection();
        }
        return id;
    }
}
