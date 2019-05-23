package mockup;

import database.Query;

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
