package d3c0de.debugger;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe debugger de Objecto facilita o rastreamento na procura de erros.
 *
 * @author d3c0de <decospdl@gmail.com>
 * @version 1.0.0
 */
public abstract class Debugger {

    private static String tab = "";

    /**
     * Verifica todos os atributos de um objeto que não seja atributo static, separando caso for container ou array e os status do objeto.
     * @param object Exemplo: (Qualquer) Object || Object [] || Collection de qualquer tipo 
     */
    public static void info(Object object) {
        if (object instanceof Collection<?>) {
            infoCollection(object);
        } else if (object != null && object.getClass().isArray()) {
            infoArray(object);
        } else {
            infoObject(object);
        }
    }

    /**
     * Separa cada objeto que contem em um array Exemplo: Object[].
     * @param object Exemplo: Object[]
     */
    private static void infoArray(Object object) {
        System.out.println("\n"+ tab + object.getClass().getSimpleName() + " (" + ((Object[]) object).length + ")");
        tab += "\t";
        for (Object o : ((Object[]) object)) {
            info(o);
        }
        tab = tab.substring(0, tab.length() - 1);
    }

    /**
     * Separa cada objeto que contem em um Containner Exemplo: LinedList, ArrayList..
     * @param object Exemplo: LinkedList, ArrayList ...
     */
    private static void infoCollection(Object object) {
        String objClass = "";
        for (Object o : (Collection<?>) object) {
            objClass = o.getClass().getSimpleName();
        }
        System.out.println("\n" + tab + ((Collection<?>) object).getClass().getSimpleName()
                + " <" + objClass + "> (" + ((Collection<?>) object).size() + ")");
        tab += "\t";
        for (Object o : (Collection<?>) object) {
            info(o);
        }
        tab = tab.substring(0, tab.length() - 1);
    }

    /**
     * Separa cada objeto para listar os seus fields (atributos)
     * @param object Exemplo: int, String, Date...
     */
    private static void infoObject(Object object) {
        if (object != null) {
            try {
                System.out.println("\n" + tab + object.getClass().getSimpleName() + " (" + object.toString() + ")");
                tab += "\t";
                Field[] fields = object.getClass().getDeclaredFields();
                infoFields(object, fields);
                tab = tab.substring(0, tab.length() - 1);
            } catch (SecurityException ex) {
                Logger.getLogger(Debugger.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("\n" + tab + "Object (NULL}");
        }
    }

    /**
     * Separa cada atributo de um objeto e lista para o usuário.
     * @param object Exemplo: int, String, Date..
     * @param fields Examplo: value, hour, minute, day ...
     */
    private static void infoFields(Object object, Field[] fields) {
        for (Field field : fields) {
            if (!Modifier.isStatic(field.getModifiers())) {
                try {
                    field.setAccessible(true);
                    System.out.println(tab + field.getName() + " = " + field.get(object));
                } catch (IllegalArgumentException | IllegalAccessException ex) {
                    Logger.getLogger(Debugger.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }   
    }
}
