package test;

import d3c0de.date.Date;
import d3c0de.finance.Money;
import d3c0de.swing.component.TableStyle;
import d3c0de.swing.table.TableModel;
import java.util.LinkedList;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

/**
 *
 * @author Andre
 */
public class PersonTab extends TableModel {

    private JTable table;
    private TableStyle style;
    private TableFilterHeader filter;
    private LinkedList<Person> persons;

    /**
     *
     * @param table
     * @param persons
     */
    public PersonTab(JTable table, LinkedList<Person> persons) {
        super(table);
        filter = new TableFilterHeader();
        this.table = table;
        this.persons = persons;
        configTableModel();
        configTableStyle();
        configTableFilter();
        createComboBoxInTable();
    }

    @Override
    public Object[][] createTableValues() {
        if (!persons.isEmpty()) {
            Object[][] values = new Object[persons.size()][5];
            for (int i = 0; i < persons.size(); i++) {
                values[i][0] = persons.get(i).getName();
                values[i][1] = persons.get(i).getAge();
                values[i][2] = (persons.get(i).getData_aniversario() == null) ? ""
                        : persons.get(i).getData_aniversario();
                values[i][3] = (persons.get(i).getCombo() == null) ? ""
                        : persons.get(i).getCombo();
                values[i][4] = persons.get(i).getMoney();
            }
            return values;
        }
        return null;
    }

    @Override
    public void updateTable(LinkedList<?> persons) {
        this.persons = (LinkedList<Person>) persons;
        loadDataTable(createTableValues());
        configTableFilter();
        table.setAutoCreateRowSorter(true);
    }

    @Override
    public void configTableModel() {
        setClassColumn(new Class[]{String.class, Integer.class, Date.class, String.class, Money.class});
        setColumnNames(new String[]{"name", "age", "data_aniversário", "combo", "money"});
        setEditableColumns(new int[]{0, 1, 2, 3, 4});
        setPrimaryKey(0);
        loadDataTable(createTableValues());
    }

    /**
     * Configuração da tabela de estilo.
     */
    @Override
    public void configTableStyle() {
        style = new TableStyle(null, null, null);
        table.setDefaultRenderer(Integer.class, style);
        table.setDefaultRenderer(String.class, style);
        table.setDefaultRenderer(Date.class, style);
        table.setDefaultRenderer(Money.class, style);
    }

    /**
     * Configuração da tabela de filtro.
     */
    @Override
    public void configTableFilter() {
        filter.setTable(table);
        filter.setAutoChoices(AutoChoices.ENABLED);
        filter.setPosition(TableFilterHeader.Position.TOP);
    }

    /**
     * Getter.
     *
     * @return lista de objetos que s~rão listado na comboBox.
     */
    private void createComboBoxInTable() {
        TableColumn combValues = table.getColumnModel().getColumn(3);
        Object[] values = new Object[]{"teste", "123", "Maluco"};
        if (values.length != 0) {
            JComboBox comboBox = new JComboBox(values);
            combValues.setCellEditor(new DefaultCellEditor(comboBox));
        }
    }
}
