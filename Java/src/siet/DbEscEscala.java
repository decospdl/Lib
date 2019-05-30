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
public abstract class DbEscEscala {

    public static final String TABLE_NAME = "db_mun.esc_escala";
    public static final String ID_ESCALA = "id_escala";
    public static final String COD_ESCALA = "cod_escala";
    public static final String ID_PROGRAMACAO = "id_programacao";

    public static boolean escalaExiste(String escala) {
        boolean existe = false;
        try {
            String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COD_ESCALA + " = '" + escala + "'";
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

    public static void inserirEscalaDb(String escala, String idProgramacao) {
        String query = "INSERT INTO " + TABLE_NAME + "(" + COD_ESCALA + ", " + ID_PROGRAMACAO + ") VALUES ('"
                + escala + "','" + idProgramacao + "')";
        ConnectionMySql.updateDb(ConfigMySql.DB_MUN, query);
    }

    public static String getIdByCodEscala(String value) {
        String idEscala = null;
        try {
            String query = "SELECT " + ID_ESCALA + " FROM " + TABLE_NAME + " WHERE "
                    + COD_ESCALA + " = '" + value + "'";
            ResultSet rs = ConnectionMySql.selectFromDb(ConfigMySql.DB_MUN, query);
            if (rs.next()) {
                idEscala = rs.getString(ID_ESCALA);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbEscEscala.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionMySql.closeConnection();
        }
        return idEscala;
    }
}
