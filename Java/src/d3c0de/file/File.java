package d3c0de.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 * Classe para tratamento de arquivos.
 *
 * @author d3c0de <decospdl@gmail.com>
 */
public abstract class File {

    /**
     * Caminho da path do projeto
     */
    public static final String PATH_PROJECT = System.getProperty("user.dir") + "\\";
    public static final String PATH_REPORTS = PATH_PROJECT + "report\\";
    public static final String PATH_EXPORT = PATH_PROJECT + "export\\";
    public static final String PATH_LOGO = PATH_PROJECT + "image\\logo\\";
    public static final String PATH_IMPORT = PATH_PROJECT + "import\\";
    public static final String PATH_CONFIG = PATH_PROJECT + "config\\";

    public static List<List<String>> getImportTable(char deliimiter, String pathFile) {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(pathFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(String.valueOf(deliimiter));
                records.add(Arrays.asList(values));
            }
        } catch (IOException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }
        return records;
    }

    public static List<String> getConfigDataBase(String database) {
        String line;
        List<String> configDb = new ArrayList<>();
        boolean hasDb = false;
        try (BufferedReader br = new BufferedReader(new FileReader(PATH_CONFIG + "config.ini"))) {
            while ((line = br.readLine()) != null) {
                if (hasDb) {
                    String aux = line.substring(line.indexOf("=")+1).replace(" ", "");
                    configDb.add(aux);
                }
                if (line.contains(database)) {
                    hasDb = true;
                }
                if (configDb.size() == 5) {
                    break;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }
        return configDb;
    }

    public static String getFile(String pathFile) {
        String line, file = "";
        try (BufferedReader br = new BufferedReader(new FileReader(pathFile))) {
            while ((line = br.readLine()) != null) {
                file += line + "\n";
            }
        } catch (IOException ex) {
            Logger.getLogger(File.class.getName()).log(Level.SEVERE, null, ex);
        }
        return file;
    }

    public static String getPathFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(fileChooser.getRootPane());
        if (option == JFileChooser.APPROVE_OPTION) {
            java.io.File file = fileChooser.getSelectedFile();
            return file.getPath();
        }
        return null;
    }

}
