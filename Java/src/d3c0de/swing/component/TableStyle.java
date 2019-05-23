package swing.component;

import java.awt.Color;
import java.awt.Component;
import java.util.LinkedList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Classe para personalização da TableModel.
 *
 * @author d3c0de <decospdl@gmail.com>
 */
public class TableStyle extends DefaultTableCellRenderer {

    private LinkedList<String> red, green, blue;

    public TableStyle(LinkedList<String> red, LinkedList<String> green, LinkedList<String> blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    /**
     * Método para edição de componente.
     *
     * @param table jTable selecionada para edição de estilo.
     * @param value o valor da célula.
     * @param isSelected true caso esteja selecionado.
     * @param hasFocus true caso tenha foco.
     * @param row a linha que o valor pertence.
     * @param column a coluna que o valor pertence.
     * @return o componente que será atribuido a célula.
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        setForeground(getColorPaint(table, row));
        if (row % 2 == 0) {
            setBackground(new Color(236, 245, 255));
        } else {
            setBackground(Color.WHITE);
        }
        
        if(table.isRowSelected(row)){
             setBackground(new Color(189, 200, 253));
        }
        
        return this;
    }

    /**
     * Verifica no vetores red, greem e blue se contém o valor da célula.
     *
     * @param table jTable selecionada para edição do estilo.
     * @param row a linha que esta sendo verificada.
     * @return a cor conforme aonde se encontra o valor.
     */
    private Color getColorPaint(JTable table, int row) {
        
        for (int i = 0; i < table.getColumnCount(); i++) {
            if (red != null && table.getValueAt(row, i) != null && red.contains(table.getValueAt(row, i).toString()))  {
                return Color.RED;
            }
            if (blue != null && table.getValueAt(row, i) != null && blue.contains(table.getValueAt(row, i).toString())) {
                return Color.BLUE;
            }
            if (green != null && table.getValueAt(row, i) != null && green.contains(table.getValueAt(row, i).toString())) {
                return new Color(0, 128, 0);
            }
        }
        return Color.BLACK;
    }
}
