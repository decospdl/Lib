package d3c0de.file;

import d3c0de.file.image.Color;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/**
 *
 * @author Andre
 */
public class Log {

    private JTextPane textPane;

    public JTextPane getTextPane() {
        return textPane;
    }

    public Log(JTextPane textPane) {
        this.textPane = textPane;
    }

    public void erroMessage(String msg) {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.RED);
        aset = sc.addAttribute(aset, StyleConstants.Background, Color.WITHE);
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);
        aset = sc.addAttribute(aset, StyleConstants.Bold, true);
        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Tahoma");
        textPane.setCaretPosition(textPane.getDocument().getLength());
        textPane.setCharacterAttributes(aset, false);
        textPane.replaceSelection(msg);
    }

    public void sucessMessage(String msg) {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.GREEN);
        aset = sc.addAttribute(aset, StyleConstants.Background, Color.WITHE);
        aset = sc.addAttribute(aset, StyleConstants.Bold, true);
        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Tahoma");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);
        textPane.setCaretPosition(textPane.getDocument().getLength());
        textPane.setCharacterAttributes(aset, false);
        textPane.replaceSelection(msg);
    }

    public void normalMessage(String msg) {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.BLACK);
        aset = sc.addAttribute(aset, StyleConstants.Background, Color.WITHE);
        aset = sc.addAttribute(aset, StyleConstants.Bold, false);
        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Tahoma");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);
        textPane.setCaretPosition(textPane.getDocument().getLength());
        textPane.setCharacterAttributes(aset, false);
        textPane.replaceSelection(msg);
    }

    public void titleMessage(String msg) {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.BLACK);
        aset = sc.addAttribute(aset, StyleConstants.Background, Color.WITHE);
        aset = sc.addAttribute(aset, StyleConstants.Bold, true);
        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Tahoma");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);
        textPane.setCaretPosition(textPane.getDocument().getLength());
        textPane.setCharacterAttributes(aset, false);
        textPane.replaceSelection(msg);
    }

    public void infoMessage(String msg) {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.GRAY);
        aset = sc.addAttribute(aset, StyleConstants.Background, Color.WITHE);
        aset = sc.addAttribute(aset, StyleConstants.Bold, false);
        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Tahoma");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);
        textPane.setCaretPosition(textPane.getDocument().getLength());
        textPane.setCharacterAttributes(aset, false);
        textPane.replaceSelection(msg);
    }

    public void highlightMessage(String msg) {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.BLUE);
        aset = sc.addAttribute(aset, StyleConstants.Background, Color.WITHE);
        aset = sc.addAttribute(aset, StyleConstants.Bold, false);
        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Tahoma");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);
        textPane.setCaretPosition(textPane.getDocument().getLength());
        textPane.setCharacterAttributes(aset, false);
        textPane.replaceSelection(msg);
    }
}
