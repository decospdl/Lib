package swing.table;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;

/**
 *
 * @author Andre
 */
public class CellEditorFormattedText extends DefaultCellEditor {

    public CellEditorFormattedText(JFormattedTextField formatedText) {
        super(new JCheckBox());
        JCheckBox checkBox = (JCheckBox) this.editorComponent;
        checkBox.removeActionListener(this.delegate);
        this.editorComponent = (JComponent) formatedText;
        this.delegate = new EditorDelegate() {
            @Override
            public void setValue(Object value) {
                ((JFormattedTextField) editorComponent).setText(value.toString());
                ((JFormattedTextField) editorComponent).setCaretPosition(0);
            }

            @Override
            public Object getCellEditorValue() {
                return ((JFormattedTextField) editorComponent).getText();
            }

            @Override
            public void cancelCellEditing() {
                super.cancelCellEditing();
            }

            @Override
            public boolean stopCellEditing() {
                return super.stopCellEditing();
            }
        };
        formatedText.addActionListener(delegate);
        setClickCountToStart(2);
    }
}
