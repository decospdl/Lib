package swing.listenner;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 * Validação de JTextField, verifica se estão todos preenchidos, para liberação
 * de um certo componente, implementado pela interface KeyListener.
 *
 * @author d3c0de <decospdl@gmail.com>
 */
public class EmptyListener implements KeyListener {

    private LinkedList<JTextField> textFields;
    private JComponent component;

    public EmptyListener(LinkedList<JTextField> textFields, JComponent component) {
        this.textFields = textFields;
        this.component = component;
    }

    /**
     * Verifica se todos os TextField estão preenchidos.
     * @return true caso todos os textFields estão preenchidos.
     */  
    private boolean isFilledAllTextFields() {
        for (JTextField tf : textFields) {
            if (tf.getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }
    
    /**
     * Quando a tecla é solta, executa a verificação.
     * @param e 
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (isFilledAllTextFields()) {
            component.setEnabled(true);
        } else {
            component.setEnabled(false);
        }
    }

}
