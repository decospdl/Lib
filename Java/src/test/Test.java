package test;

import d3c0de.date.Time;

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
        Time time = new Time("21:01:69");
        System.out.println(time.getTime(true));
    }
}
