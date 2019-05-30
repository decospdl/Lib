package d3c0de.mockup;

import d3c0de.database.ConfigMySql;
import d3c0de.database.ConnectionMySql;
import java.sql.ResultSet;

/**
 * Classe para teste da lib.
 *
 * @author deCOde <decospdl@gmail.com>
 *
 */
public class Test {

    public static void main(String[] args) throws Exception {
//        Gui gui = new Gui();
//        gui.setVisible(true);
        ResultSet rs = ConnectionMySql.selectFromDb(ConfigMySql.UNIFORM_TEST, "SELECT * FROM uniforme.peca");
        while (rs.next()) {
            System.out.println(rs.getString("descricao"));
        }
    }
}
