package test;

import d3c0de.database.ConfigMySql;
import d3c0de.database.ConnectionMySql;
import d3c0de.database.Query;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe para controle entre a classe e a tabela do DB.
 *
 * @author d3c0de <decospdl@gmail.com>
 */
public abstract class PersonDb {
    
    private static final String SCHEMA = "mydb.person";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String AGE = "age";
        
    public static LinkedList<Person> toTable() {
        LinkedList<Person> persons = new LinkedList<>();
        String query = Query.select(NAME+","+AGE, SCHEMA, null, null, null);
        try {
            ResultSet rs =  ConnectionMySql.selectFromDb(ConfigMySql.UNIFORM_TEST, query);
            while(rs.next()){
                Person p = new Person();
                p.setName(rs.getString(NAME));
                p.setAge(rs.getInt(AGE));
                persons.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonTab.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionMySql.closeConnection();
        }        
        return persons;
    }
}
