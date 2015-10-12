package ro.tekin.jpa.example;

import javax.persistence.*;

/**
 * Created by tekin.omer on 9/12/2015.
 */
//@Entity

/**
 * AccessType.FIELD is the default. It is necessary to declare this only when you want to use mixed access type.
 *
 */
@Access(AccessType.FIELD)
public class Employee3 {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    // alternative:
    @SequenceGenerator(name = "Emp_Gen", sequenceName = "Emp_Seq", allocationSize = 50, initialValue = 1)
    private Integer id;

    private String name;
    private long salary;

    @Lob
    @Basic(fetch=FetchType.LAZY)
    @Column(name = "photo")
    private byte[] picture;

    // The temporal types from the java.sql package don't need extra annotations.
    private java.sql.Date sqlDate;
    private java.sql.Time sqlTime;
    private java.sql.Timestamp sqlTimestamp;

    // The temporal types from java.util package need the @Temporal annotation to know which of the JDBC java.sql types to use
    @Temporal(TemporalType.DATE)
    private java.util.Date utilDate;

    @Temporal(TemporalType.DATE)
    private java.util.Calendar utilCalendar;

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

    // mixed access
    @Access(AccessType.PROPERTY)
    public long getSalaryForDB() {
        return salary;
    }

    public void setSalaryForDB(long salary) {
        this.salary = salary;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}
