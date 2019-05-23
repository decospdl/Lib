package d3c0de.mockup;

import d3c0de.database.Query;

/**
 * Classe para teste da lib.
 *
 * @author deCOde <decospdl@gmail.com>
 *
 */
public class Test {

    public static void main(String[] args){
//        Gui gui = new Gui();
//        gui.setVisible(true);
        System.out.println(Query.select("name, age", "mysql.person", null, null, null));
        
    }
}
