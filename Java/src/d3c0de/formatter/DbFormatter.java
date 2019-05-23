package formatter;

import date.Date;

/**
 * Classe para formatação de campos retornado pelo o database.
 *
 * @author d3c0de <decospdl@gmail.com>
 */
public abstract class DbFormatter {

    public static String toDateDb(Date date) {
        String[] split = date.getDate().split("/");
        return split[2] + "-" + split[1] + "-" + split[0] + " 00:00:00";
    }

    public static String toDateTimeDb(Date date) {
        if (date != null) {           
            String replace = date.getDateTime();
            replace = replace.replaceAll(" ", "/");
            replace = replace.replaceAll(":", "/");
            String[] split = replace.split("/");
            return split[2] + "-" + split[1] + "-" + split[0] + " " + split[5] + ":" + split[6] + ":" + split[7];
        }
        return null;
    }

    /**
     * Converte campos do formato YYYY-MM-dd HH:mm:ss para dd/MM/YYYY HH:mm:ss.
     *
     * @param date data a ser convertida YYYY-MM-dd HH:mm:ss
     * @return o valor data ajustada dd/MM/YYYY HH:mm:ss
     */
    public static Date dateDbTo(String date) {
        if (date != null) {
            date = date.replaceAll("-", "/");
            String[] split = date.split("/");
            return new Date(split[2].substring(0, 2) + "/" + split[1] + "/" + split[0], "00:00:00");
        }
        return null;
    }

    /**
     * Converte campos do formato YYYY-MM-dd HH:mm:ss para dd/MM/YYYY HH:mm:ss.
     *
     * @param date data a ser convertida YYYY-MM-dd HH:mm:ss
     * @return o valor data ajustada dd/MM/YYYY HH:mm:ss
     */
    public static Date dateTimeDbTo(String date) {
        if (date != null) {
            date = date.replaceAll("-", "/");
            date = date.replaceAll(" ", "/");
            date = date.replaceAll(":", "/");
            date = date.replaceAll(":", "/");
            String[] split = date.split("/");
            return new Date(split[2] + "/" + split[1] + "/" + split[0],
                    split[3] + ":" + split[4] + ":" + split[5].substring(0, 1));
        }
        return null;
    }
}
