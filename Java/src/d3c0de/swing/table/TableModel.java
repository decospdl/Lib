package d3c0de.swing.table;

import java.awt.Component;
import java.awt.FontMetrics;
import java.util.LinkedList;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * Classe para controle da JTable.
 *
 * @author d3c0de <decospdl@gmail.com>
 */
public abstract class TableModel extends AbstractTableModel implements TableModelListener {

    private final LinkedList<Object> changedObjects = new LinkedList<>();
    private final LinkedList<Object> changedIndexes = new LinkedList<>();
    private final LinkedList<String> changedColumns = new LinkedList<>();
    private int changedRow, changedColumn;

    private JTable table;
    private String[] nameColumns;
    private int[] editableColumns;
    private int primaryKey;
    private int rowCount;
    private Object[][] tableValue;
    private Class[] columnClasses;
    private ListSelectionListener lss;
    private String selectedRow;

    public TableModel(JTable table) {
        setTable(table);
        this.addTableModelListener(this);
        listennerSelectedRow();
    }

    /**
     * Ações de alteração na tabela todas vez que acionada é guardado em vetores
     * o valor, a coluna e o index alterado.
     *
     * @param e informações do evento.
     */
    @Override
    public void tableChanged(TableModelEvent e) {
        changedRow = e.getFirstRow();
        changedColumn = e.getColumn();
        TableModel model = (TableModel) e.getSource();
        changedObjects.add(model.getValueAt(changedRow, changedColumn));
        changedIndexes.add(model.getValueAt(changedRow, primaryKey));
        changedColumns.add(model.getColumnName(changedColumn));
    }

    /**
     * Após a inserção dos dados deve ser executado a limpeza.
     */
    public void limparVariaveis() {
        changedObjects.clear();
        changedIndexes.clear();
        changedColumns.clear();
    }

    /**
     * Getter.
     *
     * @return o valor do Objeto contido na celula.
     */
    public LinkedList<Object> getChangedObjects() {
        return changedObjects;
    }

    /**
     * Getter.
     *
     * @return o index que se encontra o objeto.
     */
    public LinkedList<Object> getChangedIndexex() {
        return changedIndexes;
    }

    /**
     * Getter.
     *
     * @return o nome da coluna que se encontra o objeto.
     */
    public LinkedList<String> getChangedColumns() {
        return changedColumns;
    }

    /**
     * Ajusta automáticamente as colunas da tabela.
     */
    public void autoSizeColumn() {
        JTableHeader tableHeader = table.getTableHeader();
        int[] minWidths = new int[table.getColumnCount()];
        int[] maxWidths = new int[table.getColumnCount()];
        if (tableHeader != null) {
            getMaxMinWidths(minWidths, maxWidths);
        }
        adjustMaximumWidths(maxWidths);
        setMaxMinWidths(minWidths, maxWidths);
    }

    /**
     * Retora a largura das coluna o máximo e o minimo.
     *
     * @param minWidths lista com os tamannhos minimo da coluna.
     * @param maxWidths lista com os tamaanhos máximo da coluna.
     */
    private void getMaxMinWidths(int[] minWidths, int[] maxWidths) {
        JTableHeader tableHeader = table.getTableHeader();
        FontMetrics headerFontMetrics = tableHeader.getFontMetrics(tableHeader.getFont());
        for (int i = 0; i < table.getColumnCount(); i++) {
            int headerWidth = headerFontMetrics.stringWidth(table.getColumnName(i));
            minWidths[i] = headerWidth;
            int maxWidth = getMaxColumnElementWidth(i, headerWidth);
            maxWidths[i] = Math.max(maxWidth, minWidths[i]);
        }
    }

    /**
     * Atribui a largura das coluna o máximo e o minimo.
     *
     * @param minWidths lista com os tamannhos minimo da coluna.
     * @param maxWidths lista com os tamaanhos máximo da coluna.
     */
    private void setMaxMinWidths(int[] minWidths, int[] maxWidths) {
        for (int i = 0; i < minWidths.length; i++) {
            if (minWidths[i] > 0) {
                table.getColumnModel().getColumn(i).setMinWidth(minWidths[i]);
            }
            if (maxWidths[i] > 0) {
                table.getColumnModel().getColumn(i).setPreferredWidth(maxWidths[i]);
            }
        }
    }

    /**
     * O tamanho do maior elemento da coluna.
     *
     * @param columnIndex o index da coluna da tabela a ser procurado.
     * @param headerWidth o tamanho do titulo da coluna.
     * @return o valor do maior tamanho da coluna.
     */
    private int getMaxColumnElementWidth(int columnIndex, int headerWidth) {
        int maxWidth = headerWidth;
        TableColumn column = table.getColumnModel().getColumn(columnIndex);
        TableCellRenderer cellRenderer = column.getCellRenderer();
        if (cellRenderer == null) {
            cellRenderer = new DefaultTableCellRenderer();
        }
        for (int row = 0; row < table.getModel().getRowCount(); row++) {
            Component rendererComponent = cellRenderer.getTableCellRendererComponent(table,
                    table.getModel().getValueAt(row, columnIndex), false, false, row, columnIndex);

            double valueWidth = rendererComponent.getPreferredSize().getWidth();
            maxWidth = (int) Math.max(maxWidth, valueWidth);
        }
        return maxWidth;
    }

    /**
     * Caso a tabela ultrapasse o tamanho cabivel na tela, vai roubando 1 pixel
     * da maior coluna, até que consigar ajustar todas colunas.
     *
     * @param maxWidths a lista dos maiores elemento de cada coluna.
     */
    private void adjustMaximumWidths(int[] maxWidths) {
        if (table.getWidth() > 0) {
            while (sum(maxWidths) > table.getWidth()) {
                int highestWidthIndex = findLargestIndex(maxWidths);
                maxWidths[highestWidthIndex] -= 1;
            }
        }
    }

    /**
     * Encontra o index do elemento com o maior tamanho.
     *
     * @param widths lista com toos os tamanhos.
     * @return o index do elemento com o maior tamanho.
     */
    private int findLargestIndex(int[] widths) {
        int largestIndex = 0;
        int largestValue = 0;
        for (int i = 0; i < widths.length; i++) {
            if (widths[i] > largestValue) {
                largestIndex = i;
                largestValue = widths[i];
            }
        }
        return largestIndex;
    }

    /**
     * Soma todas os tamanhos.
     *
     * @param widths lista com todos os tamanhos.
     * @return a soma dos tamanhos.
     */
    private int sum(int[] widths) {
        int sum = 0;

        for (int width : widths) {
            sum += width;
        }

        return sum;
    }

    /**
     * Getter.
     *
     * @param index o index da coluna que procura.
     * @return o nome da coluna conforme o index.
     */
    @Override
    public String getColumnName(int index) {
        return nameColumns[index];
    }

    /**
     * O valor do endereço conforme linha e coluna.
     *
     * @param rowIndex a linha do valor.
     * @param columnIndex a coluna do valor.
     * @return
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return tableValue[rowIndex][columnIndex];
    }

    /**
     * Setter.
     *
     * @param aValue o valor que será atribuido na linha e coluna específica.
     * @param rowIndex a linha da célula.
     * @param columnIndex a coluna da célula.
     */
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        tableValue[rowIndex][columnIndex] = aValue;
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    /**
     * Células que serão ativas para edição.
     *
     * @param row linha que será liberado a edição.
     * @param col coluna que será liberado a edição.
     * @return true editável, false não editável.
     */
    @Override
    public boolean isCellEditable(int row, int col) {
        if (editableColumns != null) {
            for (int colunaEditavel : editableColumns) {
                if (colunaEditavel == col) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Getter.
     *
     * @return quantidade de linhas na tabela.
     */
    @Override
    public int getRowCount() {
        return rowCount;
    }

    /**
     * Getter.
     *
     * @return quantidade de colunas na tabela.
     */
    @Override
    public int getColumnCount() {
        return nameColumns.length;
    }

    /**
     * Getter+
     *
     * @param column o index da coluna.
     * @return o tipo de classe que é formado a coluna.
     */
    @Override
    public Class getColumnClass(int column) {
        return columnClasses[column];
    }

    /**
     * Inicia o listener para identificadação de qual célula esta sendo
     * selecionada.
     */
    public void listennerSelectedRow() {
        table.getSelectionModel().removeListSelectionListener(lss);
        lss = (ListSelectionEvent e) -> {
            if (table.getSelectedRow() < tableValue.length && table.getSelectedRow() >= 0) {
                selectedRow = table.getValueAt(table.getSelectedRow(), 0).toString();
            }
        };
        table.getSelectionModel().addListSelectionListener(lss);
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    /**
     * Setter.
     *
     * @param nameColumns lista com os nomes da coluna.
     */
    public void setColumnNames(String[] nameColumns) {
        this.nameColumns = nameColumns;
    }

    /**
     * Setter.
     *
     * @param rowCount a quantidade de linhas da tabela, ou tamanho da lista que
     * será atribuido
     *
     */
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    /**
     * Setter.
     *
     * @param editableColumns lista com os indexes das colunas editáveis.
     */
    public void setEditableColumns(int[] editableColumns) {
        this.editableColumns = editableColumns;
    }

    /**
     * Setter.
     *
     * @param primaryKey o index de onde se encontra a primary key para
     * identificação da linha.
     */
    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
    }

    /**
     * Setter.
     *
     * @param tableValue os valores contido na tabela.
     */
    public void setTableValue(Object[][] tableValue) {
        this.tableValue = tableValue;
    }

    /**
     * Setter.
     *
     * @param columnClasses lista com as classes de cada coluna.
     */
    public void setClassColumn(Class[] columnClasses) {
        this.columnClasses = columnClasses;
    }

    public String getSelectedRow() {
        return selectedRow;
    }

    public void loadDataTable(Object[][] object) {
        setTableValue(object);
        table.setModel(this);
        setRowCount((object == null) ? 0 : object.length);
        autoSizeColumn();
    }

    public abstract Object[][] createTableValues();

    public abstract void updateTable(LinkedList<?> persons);

    public abstract void configTableModel();

    public abstract void configTableStyle();

    public abstract void configTableFilter();

}
