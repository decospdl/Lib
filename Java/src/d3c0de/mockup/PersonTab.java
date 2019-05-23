package mockup;

import swing.component.TableStyle;
import swing.table.TableModel;
import java.util.LinkedList;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
        model.setClassColumn(new Class[]{String.class, Integer.class});
        model.setColumnNames(new String[]{"name", "age"});
        model.setEditableColumns(new int[]{0, 1});
        model.setPrimaryKey(0);
        model.setRowCount((tableValues == null) ? 0 : tableValues.length);
        model.start();
    }

    private void createTableValues(LinkedList<Person> persons) {
        Object[][] values = new Object[persons.size()][2];
        for (int i = 0; i < persons.size(); i++) {
            values[i][0] = persons.get(i).getName();
            values[i][1] = persons.get(i).getAge();
        }
        tableValues = values;
    }

    public void updateTable(LinkedList<Person> persons) {
        createTableValues(persons);
        configTableModel();
        configTableStyle();
        configTableFilter();
        listennerSelectedRow();
    }

    /**
     * Configuração da tabela de estilo.
     */
    public void configTableStyle() {
        style = new TableStyle(null, null, null);
        table.setDefaultRenderer(Integer.class, style);
        table.setDefaultRenderer(String.class, style);
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
}
