package ro.tekin.jpa.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by tekin.omer on 10/11/2015.
 */
@Entity
public class Project {
    @TableGenerator(name = "ID_GEN", table = "T_ID_GEN", pkColumnValue = "PROJ_GEN", pkColumnName = "GEN_NAME", valueColumnName = "ID")

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_GEN") // the default
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "projects", cascade = CascadeType.ALL)
    private Collection<Employee> employees;

    public Project(String name) {
        this.name = name;
    }

    public Project() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Collection<Employee> employees) {
        this.employees = employees;
    }
}
