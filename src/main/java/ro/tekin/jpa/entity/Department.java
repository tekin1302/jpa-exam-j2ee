package ro.tekin.jpa.entity;

import javax.persistence.*;
import java.util.Map;

/**
 * Created by tekin.omer on 10/12/2015.
 */
@Entity
public class Department {
    @TableGenerator(name = "ID_GEN", table = "T_ID_GEN", pkColumnValue = "DEP_GEN", pkColumnName = "GEN_NAME", valueColumnName = "ID")

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_GEN") // the default
    private Integer id;

    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "DEPT_EMP", joinColumns = @JoinColumn(name = "DEPT_ID"), inverseJoinColumns = @JoinColumn(name = "EMP_ID"))
    @MapKeyColumn(name = "CUB_ID")
    private Map<String, Employee> employeesByCubicle;

    @ElementCollection
    @CollectionTable(name = "EMP_SENIORITY", joinColumns = @JoinColumn(name = "DEPT_ID"))
    @MapKeyJoinColumn(name = "EMP_ID")
    @Column(name = "SENIORITY")
    private Map<Employee, Integer> seniorities;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Map<String, Employee> getEmployeesByCubicle() {
        return employeesByCubicle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmployeesByCubicle(Map<String, Employee> employeesByCubicle) {
        this.employeesByCubicle = employeesByCubicle;
    }

    public Map<Employee, Integer> getSeniorities() {
        return seniorities;
    }

    public void setSeniorities(Map<Employee, Integer> seniorities) {
        this.seniorities = seniorities;
    }
}
