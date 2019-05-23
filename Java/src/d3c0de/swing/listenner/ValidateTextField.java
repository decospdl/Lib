package swing.listenner;

import java.awt.Toolkit;
import java.util.HashMap;
import java.util.regex.Pattern;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Classe para validação dos canmpos de input do usuário, implementando a
 * interface PlainDocument.
 *
 * @author d3c0de <decospdl@gmail.com>
 */
public class ValidateTextField extends PlainDocument {

    private int max;
    private String regExp;
    private String text;
    private HashMap mask;

    /**
     * Construtor default.
     *
     * @param max o tamanh máximo do input que o usuário pode digitar.
     * @param regExp a padrão que deve ser aceito no formato de RegExp.
     * @param mask chars fixos que serão gerados automáticamente ao digitar,
     * conforme a "key" localização do char, e o "valor" char que será
     * atribuido.
     */
    public ValidateTextField(int max, String regExp, HashMap mask) {
        this.max = max;
        this.regExp = regExp;
        this.mask = mask;
    }

    /**
     * Insere os caracteres conforme a regex e a maskara atribuida no
     * construtor.
     *
     * @param offs
     * @param str
     * @param a
     * @throws BadLocationException
     */
    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        Pattern setence = Pattern.compile(regExp);
        text = getText(0, getLength()) + str;
        if (mask.containsKey(offs) && str != null) {
            super.insertString(offs, mask.get(offs).toString(), a);
        }
        if (!setence.matcher(text).matches() || str == null || text.length() > max) {
            str = null;
            Toolkit.getDefaultToolkit().beep();
        }
        super.insertString(offs, str, a);
    }
}
