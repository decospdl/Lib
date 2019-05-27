package d3c0de.file.image;

import d3c0de.file.File;

/**
 * Enum com todos os logos disponiveis no projeto.
 *
 * @author d3c0de <decospdl@gmail.com>
 */
public enum Logo {
    ESTRELA(File.PATH_LOGO + "estrela.png"),
    INSULAR(File.PATH_LOGO + "\\images\\logo\\insular.png"),
    C_FENIX(File.PATH_LOGO + "\\images\\logo\\consorcio_fenix.png");

    private String path;

    private Logo(String path) {
        this.path = path;
    }

    /**
     * Caminho específicdo no construtor do enum
     *
     * @return o caminho do logo.
     */
    public String getPath() {
        return path;
    }
}
