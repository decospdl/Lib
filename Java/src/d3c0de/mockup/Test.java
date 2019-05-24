package d3c0de.mockup;

import d3c0de.date.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

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
        Time time = new Time();
        time.setTime("01:10:12");
        System.out.println(LocalTime.of(00, 01, 59).toSecondOfDay());
        
    }
}
