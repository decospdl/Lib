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
public class DbEscEvento {

    private static final String TABLE_NAME = "db_mun.esc_evento";
    private static final String ID = "id_evento";
    private static final String DESCRICAO = "descricao";

    public static boolean eventoExiste(String idEvento) {
        boolean existe = false;
        try {
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = '" + idEvento + "'";
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
}
