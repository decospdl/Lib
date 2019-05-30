package d3c0de.mockup;

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
public class DbGeralLinha {

    public static final String TABLE_NAME = "db_mun.geral_linha";
    public static final String ID = "id";
    public static final String LINHA = "id_oficial";

    public static boolean linhaExiste(String linha) {
        boolean existe = false;
        try {
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + LINHA + " = '" + linha + "'";
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

    public static String getIdByLinha(String linha) {
        String id = null;
        try {
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + LINHA + " = '" + linha + "'";
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
