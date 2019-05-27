package d3c0de.mockup;

import com.michaelbaranov.microba.calendar.DatePicker;
import d3c0de.swing.table.CellEditorDatePicker;
import swing.component.TableStyle;
import d3c0de.swing.table.TableModel;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

/**
 *
 * @author Andre
 */
public class PersonTab {

    private JTable table;
    private Object[][] tableValues;
    private TableModel model;
    private TableStyle style;
    private TableFilterHeader filter;
    private ListSelectionListener lss;
    private String selectedRow;

    public PersonTab(JTable table, LinkedList<Person> persons) {
        filter = new TableFilterHeader();
        this.table = table;
        updateTable(persons);
    }

    /**
     * Configuração da tabela modelo.
     */
    public void configTableModel() {
        model = new TableModel(table, tableValues);
        model.setClassColumn(new Class[]{String.class, Integer.class, Date.class});
        model.setColumnNames(new String[]{"name", "age", "data_aniversário"});
        model.setEditableColumns(new int[]{0, 1, 2});
        model.setPrimaryKey(0);
        model.setRowCount((tableValues == null) ? 0 : tableValues.length);
        model.start();
    }

    private void createTableValues(LinkedList<Person> persons) {
        if (persons != null) {
            Object[][] values = new Object[persons.size()][3];
            for (int i = 0; i < persons.size(); i++) {
                values[i][0] = persons.get(i).getName();
                values[i][1] = persons.get(i).getAge();
                values[i][2] = persons.get(i).getData_aniversario();
            }
            tableValues = values;
        }
    }

    public void updateTable(LinkedList<Person> persons) {
        createTableValues(persons);
        configTableModel();
        configTableStyle();
        configTableFilter();
        listennerSelectedRow();
        createComboBoxInTable();
    }

    /**
     * Configuração da tabela de estilo.
     */
    public void configTableStyle() {
        style = new TableStyle(null, null, null);
        table.setDefaultRenderer(Integer.class, style);
        table.setDefaultRenderer(String.class, style);
        table.setDefaultRenderer(Date.class, style);
    }

    /**
     * Configuração da tabela de filtro.
     */
    public void configTableFilter() {
        filter.setTable(table);
        filter.setAutoChoices(AutoChoices.ENABLED);
        filter.setPosition(TableFilterHeader.Position.TOP);
    }

    /**
     * Inicia o listener para identificadação de qual célula esta sendo
     * selecionada.
     *
     * @param tabela a tabela que esta sendo atribuido o listener.
     */
    private void listennerSelectedRow() {
        table.getSelectionModel().removeListSelectionListener(lss);
        lss = (ListSelectionEvent e) -> {
            if (table.getSelectedRow() < tableValues.length && table.getSelectedRow() >= 0) {
                selectedRow = table.getValueAt(table.getSelectedRow(), 0).toString();
            }
        };
        table.getSelectionModel().addListSelectionListener(lss);
    }

    /**
     * Getter.
     *
     * @return componente JTable utilizado para população da tabela.
     */
    public JTable getTable() {
        return table;
    }

    /**
     * Getter.
     *
     * @return modelo que utilzado pela jTable.
     */
    public TableModel getTableModel() {
        return model;
    }

    /**
     * Getter.
     *
     * @return tabela de estilo que redenriza a JTable.
     */
    public TableStyle getTableStyle() {
        return style;
    }

    /**
     * Getter.
     *
     * @return o filtro parecido com excel, vindo com a lib.
     */
    public TableFilterHeader getTableFilter() {
        return filter;
    }

    /**
     * Getter.
     *
     * @return lista de objetos que s~rão listado na comboBox.
     */
    private void createComboBoxInTable() {
        TableColumn dateValue = table.getColumnModel().getColumn(2);
        DatePicker datePicker = new DatePicker(new Date());
        dateValue.setCellEditor(new CellEditorDatePicker(datePicker));
    }
}
