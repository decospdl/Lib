package d3c0de.database;

import d3c0de.file.File;
import java.util.List;

/**
 * Classe com todos os dados para conexão no banco de dados MYSQL.
 *
 * @author deCOde <decospdl@gmail.com>
 */
public enum ConfigMySql {
    TEST("", "", "", "", ""),
    UNIFORM_TEST("", "", "", "", ""),
    DB_MUN("", "", "", "", "");

    static {
        configDb(TEST);
        configDb(UNIFORM_TEST);
        configDb(DB_MUN);
    }

    private static void configDb(ConfigMySql db) {
        List<String> configDb = File.getConfigDataBase(db.name());
        db.server = configDb.get(0);
        db.database = configDb.get(1);
        db.config = configDb.get(2);
        db.username = configDb.get(3);
        db.password = configDb.get(4);
    }

    /**
     * Nome do server que será acessado.
     */
    private String server;

    /**
     * Nome do database que será acessado
     */
    private String database;

    /**
     * As configurações para acesso ao database.
     */
    private String config;

    /**
     * O usuário para acesso ao database.
     */
    private String username;

    /**
     * Senha do usuário para acesso ao database.
     */
    private String password;

    private ConfigMySql(String server, String database, String config, String username, String password) {
        this.server = server;
        this.database = database;
        this.config = config;
        this.username = username;
        this.password = password;
    }

    /**
     * Url para conexão do Driver Mysql.
     *
     * @return o valor da url para conexão.
     */
    public String getUrl() {
        return "jdbc:mysql://" + server + "/" + database + "?" + config;
    }

    /**
     * Usuário para acesso ao database.
     *
     * @return o nome do usuário.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Senha do usuário para acesso ao database.
     *
     * @return a senha do usuário.
     */
    public String getPassword() {
        return password;
    }
}
