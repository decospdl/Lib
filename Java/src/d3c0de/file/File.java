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
import javax.swing.plaf.RootPaneUI;

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

    public static List<List<String>> getImport(char deliimiter, String pathFile) {
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
    
    public static String getPathFile(){
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(fileChooser.getRootPane());
        if(option == JFileChooser.APPROVE_OPTION){
            java.io.File file = fileChooser.getSelectedFile();
            return file.getPath();
        }
        return null;
    }
    
    
}
