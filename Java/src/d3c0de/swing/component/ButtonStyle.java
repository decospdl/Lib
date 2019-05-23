package d3c0de.swing.component;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ButtonStyle extends JButton implements TableCellRenderer {

    public ButtonStyle() {
        setOpaque(false);
        setText("Link");
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        return this;
    }
}
