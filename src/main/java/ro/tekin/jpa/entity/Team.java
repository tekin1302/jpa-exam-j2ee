package ro.tekin.jpa.entity;

import javax.persistence.*;
import java.util.Map;

/**
 * Created by tekin.omer on 10/12/2015.
 */
@Entity
public class Team {
    @TableGenerator(name = "ID_GEN", table = "T_ID_GEN", pkColumnValue = "TEAM_GEN", pkColumnName = "GEN_NAME", valueColumnName = "ID")

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_GEN") // the default
    private Integer id;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    @MapKey(name = "id") // by default the entities are keyed by their primary key (it is a field from the target)
    private Map<Integer, Employee> employees;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Map<Integer, Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Map<Integer, Employee> employees) {
        this.employees = employees;
    }
}
