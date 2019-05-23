package reports;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintPage;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Classe para controlar gerador de reltaórios iReport.
 *
 * @author d3c0de <decospdl@gmail.com>
 */
public class Reports {

    /**
     * Lista de relatórios para ser exibido ou exportado.
     */
    private List<JasperPrint> jpList = new ArrayList<>();

    /**
     * Utiliza o primeiro relatório para receber as páginas dos relatórios
     * subsequentes.
     *
     * @param jp o primeiro relatório que recenerá as outras páginas.
     * @param newJp o relatório subsequente que enviará suas pagina ao primeiro
     * relatório.
     * @return a fusão dos relatórios.
     */
    private JasperPrint addPage(JasperPrint jp, JasperPrint newJp) {
        List<JRPrintPage> pages = newJp.getPages();
        for (JRPrintPage page : pages) {
            jp.addPage(page);
        }
        return jp;
    }

    /**
     * Exibe o relatório para o usuário.
     */
    public void showReports() {
        JasperPrint jp = jpList.get(0);
        for (int i = 1; i < jpList.size(); i++) {
            jp = addPage(jp, jpList.get(i));
        }
        JasperViewer view = new JasperViewer(jp, false);
        view.setVisible(true);
    }

    /**
     * Exporta o relatório para o caminho expecífico no formato PDF.
     *
     * @param path caminho que será salvo o relatório.
     */
    public void exportPdf(String path) {
        try {
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(SimpleExporterInput.getInstance(jpList));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
            SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
            configuration.setCreatingBatchModeBookmarks(true);
            exporter.setConfiguration(configuration);
            exporter.exportReport();
        } catch (JRException ex) {
            Logger.getLogger(Reports.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Adiciona o relatório sem vínculo de conexão com query a lista.
     *
     * @param path a localização do arquivo jrxml.
     * @param parameters os parâmetro necessário para executar ou demonstrar no
     * relatório.
     */
    public void addJasperPrint(String path, Map parameters) {
        try {

            JasperDesign draw = JRXmlLoader.load(path);
            JasperReport report = JasperCompileManager.compileReport(draw);
            jpList.add(JasperFillManager.fillReport(report, parameters, new net.sf.jasperreports.engine.JREmptyDataSource()));
        } catch (JRException ex) {
            Logger.getLogger(Reports.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Adiciona o relatório com vínculo conexão de result set a lista.
     *
     * @param path a localização do arquivo jrxml.
     * @param parameters os parâmetro necessário para executar ou demonstrar no
     * relatório.
     * @param rs os dados gerada pela query.
     */
    public void addJasperPrint(String path, Map parameters, ResultSet rs) {
        try {
            JasperDesign draw = JRXmlLoader.load(path);
            JasperReport report = JasperCompileManager.compileReport(draw);
            JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);
            jpList.add(JasperFillManager.fillReport(report, parameters, jrRS));
        } catch (JRException ex) {
            Logger.getLogger(Reports.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
