package test;

import d3c0de.date.Date;


/**
 *
 * @author Andre
 */
public class Person {
    private String name;
    private Integer age;
    private Date data_aniversario;
    
    public Person(){
        
    }

    public Person(String name, Integer age, Date data_aniversario) {
        this.name = name;
        this.age = age;
        this.data_aniversario = data_aniversario;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getData_aniversario() {
        return data_aniversario;
    }

    public void setData_aniversario(Date data_aniversario) {
        this.data_aniversario = data_aniversario;
    }   
}
