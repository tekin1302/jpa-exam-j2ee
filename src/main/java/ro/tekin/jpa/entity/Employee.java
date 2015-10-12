package ro.tekin.jpa.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by tekin.omer on 9/12/2015.
 */
@Entity
public class Employee {
    @TableGenerator(name = "ID_GEN", table = "T_ID_GEN", pkColumnValue = "EMP_GEN", pkColumnName = "GEN_NAME", valueColumnName = "ID")

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_GEN") // the default
    private Integer id;
    private String name;
    private long salary;

    @Embedded // optional
    @Basic(fetch=FetchType.LAZY)
    @AttributeOverrides({@AttributeOverride(name = "city", column = @Column(name = "CITY")),
                            @AttributeOverride(name = "street", column = @Column(name = "STREET"))})
    private Address address;

    @Lob
    @Basic(fetch=FetchType.LAZY)
    @Column(name = "photo")
    private byte[] picture;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "EMP_PROJ", joinColumns = @JoinColumn(name = "EMP_ID"), inverseJoinColumns = @JoinColumn(name = "PROJ_ID"))
    @OrderBy("name DESC")
    private List<Project> projects;

    @ElementCollection(targetClass = VacantionEntry.class)
    @CollectionTable(name = "VACATION", joinColumns = @JoinColumn(name = "EMP_ID"))
    @AttributeOverride(name = "daysTaken", column = @Column(name = "DAYS_ABS"))
    private Collection vacationBookings;

    @ElementCollection
    @Column(name = "PORECLE") // default is NICKNAMES
    private Set<String> nickNames;

    @ElementCollection(/*targetClass = String.class */ /*should be used whenever the value type cannot be deduced from the attribute definition*/)
    @CollectionTable(name = "EMP_PHONE") // default is EMPLOYEE_PHONENUMBERS
    @MapKeyColumn(name = "PHONE_TYPE") // default is PHONENUMBERS_KEY
//    @MapKeyClass(PhoneType.class) // must be used whenever the key type cannot be deduced from the attribute definition;
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "PHONE_NUM") // default is PHONENUMBERS
    private Map<PhoneType, String> phoneNumbers;

    @ManyToOne(cascade = CascadeType.ALL)
    private Team team;

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
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

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Collection getVacationBookings() {
        return vacationBookings;
    }

    public void setVacationBookings(Collection vacationBookings) {
        this.vacationBookings = vacationBookings;
    }

    public Set<String> getNickNames() {
        return nickNames;
    }

    public void setNickNames(Set<String> nickNames) {
        this.nickNames = nickNames;
    }

    public Map<PhoneType, String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Map<PhoneType, String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
