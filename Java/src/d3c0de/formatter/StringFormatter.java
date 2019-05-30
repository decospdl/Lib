package d3c0de.formatter;

/**
 *
 * @author d3c0de <decospdl@gmail.com>
 */
public abstract class StringFormatter {

    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int CENTER = 3;

    public static String leftPad(String word, int length) {
        return String.format("%" + length + "." + word.length() + "s", word);
    }

    public static String rightPad(String word, int length) {
        return String.format("%-" + length + "." + word.length() + "s", word);
    }

    public static String centerPad(String word, int length) {
        int halfLength = word.length() / 2;
        int newLength = (word.length() % 2 == 0) ? (word.length() + 1) : word.length();
        return String.format("%" + (length - halfLength)  
                + "s%" + newLength + "s%-" + (length - halfLength) + "s", "", word, "");
    }

    private static String padTo(int pad, String value, int length) {
        String padValue;
        switch (pad) {
            case 1:
                padValue = leftPad(value, length);
                break;
            case 2:
                padValue = rightPad(value, length);
                break;
            case 3:
                padValue = centerPad(value, length);
                break;
            default:
                padValue = null;
        }
        return padValue;
    }

    public static String createLine(int size) {
        String line = "";
        for (int i = 0; i < size; i++) {
            line += "-";
        }
        return line;
    }

    public static String createLine(String[] values) {
        String line = "";
        for (String value : values) {
            line += createLine(value.length());
        }
        return line;
    }

    public static String createTable(String[][] values, int length, int padTo) {
        String aux = "", table = "";
        for (String[] list : values) {
            aux = "";
            for (String value : list) {
                aux += padTo(padTo, value, length);
            }
            table += createLine(aux.length() - 2) + "\n";
            table += aux + "\n";
        }
        table += createLine(aux.length() - 2) + "\n";
        return table.replace("||", "|");
    }
}
