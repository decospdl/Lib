package test;

import d3c0de.swing.component.TableStyle;
import d3c0de.swing.table.TableModel;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.JTable;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

/**
 *
 * @author Andre
 */
public class PersonTab extends TableModel {

    private JTable table;
    private LinkedList<Person> persons;
    private TableStyle style;
    private TableFilterHeader filter;

    /**
     *
     * @param table
     * @param persons
     */
    public PersonTab(JTable table, LinkedList<Person> persons) {
        this.filter = new TableFilterHeader();
        this.table = table;
        this.persons = persons;
        configTableModel();
        configTableStyle();
        configTableFilter();
    }

    private Object[][] createTableValues() {
        if (!persons.isEmpty()) {
            Object[][] values = new Object[persons.size()][3];
            for (int i = 0; i < persons.size(); i++) {
                values[i][0] = persons.get(i).getName();
                values[i][1] = persons.get(i).getAge();
                values[i][2] = (persons.get(i).getData_aniversario() == null) ? "" : 
                        persons.get(i).getData_aniversario().getDate();
            }
            return values;
        }
        return null;
    }

    public void updateTable(LinkedList<Person> persons) {
        this.persons = persons;
        setTable(table);
        setTableValue(createTableValues());
        setRowCount((persons.isEmpty()) ? 0 : persons.size());
        configTable();
    }

    private void configTableModel() {
        setTable(table);
        setTableValue(createTableValues());
        setClassColumn(new Class[]{String.class, Integer.class, String.class});
        setColumnNames(new String[]{"name", "age", "data_aniversário"});
        setEditableColumns(new int[]{0, 1, 2});
        setPrimaryKey(0);
        setRowCount((persons.isEmpty()) ? 0 : persons.size());
        listennerSelectedRow();
        configTable();
    }

    /**
     * Configuração da tabela de estilo.
     */
    private void configTableStyle() {
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
}
