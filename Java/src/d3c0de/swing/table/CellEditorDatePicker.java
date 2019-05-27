package d3c0de.swing.table;

import com.michaelbaranov.microba.calendar.DatePicker;
import java.beans.PropertyVetoException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComponent;

/**
 *
 * @author Andre
 */
public class CellEditorDatePicker extends DefaultCellEditor {
    public CellEditorDatePicker(DatePicker datePicker) {
        super(new JCheckBox());
        JCheckBox checkBox = (JCheckBox) this.editorComponent;
        checkBox.removeActionListener(this.delegate);
        this.editorComponent = (JComponent) datePicker;
        this.delegate = new DefaultCellEditor.EditorDelegate() {
            @Override
            public void setValue(Object value) {
                try {
                    ((DatePicker) editorComponent).setDate((Date) value);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(CellEditorDatePicker.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public Object getCellEditorValue() {
                return ((DatePicker) editorComponent).getDate();
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
        datePicker.addActionListener(delegate);
        setClickCountToStart(1);
    }
}
