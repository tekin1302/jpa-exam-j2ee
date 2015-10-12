package ro.tekin.jpa.service;

import org.springframework.stereotype.Service;
import ro.tekin.jpa.entity.*;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.*;

/**
 * Created by tekin on 10/12/2015.
 */
@Service
public class MyServiceImpl implements MyService{
    EntityManager em;

    @PersistenceContext(unitName = "exam")
    public void setEm(EntityManager em) {
        this.em = em;
    }

    @PostConstruct
    public void start() {
//        method1();
//        method2();
    }
    public void method2() {
        List<Employee> employees = em.createQuery("select e from Employee e", Employee.class).getResultList();
        List<Department> departments = em.createQuery("select e from Department e", Department.class).getResultList();
        List<Team> teams = em.createQuery("select e from Team e", Team.class).getResultList();
        System.out.println(employees.size());
    }

    public void method1() {
        Employee employee = new Employee();
        employee.setName("Tekin");
        employee.setSalary(2000);
        List<Project> projects = new ArrayList<Project>();
        projects.add(new Project("Proiect 1"));
        projects.add(new Project("Proiect 2"));
        employee.setProjects(projects);

        Address address = new Address();
        address.setCity("Medgidia");
        address.setStreet("Independentei");
        address.setZipCode("079832");

        employee.setAddress(address);

        Set<String> nickNames = new HashSet<String>();
        nickNames.add("Boss");
        nickNames.add("Sef");
        nickNames.add("Barosanu'");

        employee.setNickNames(nickNames);

        Collection vacationBookings = new ArrayList();
        vacationBookings.add(new VacantionEntry(Calendar.getInstance(), 44));
        vacationBookings.add(new VacantionEntry(Calendar.getInstance(), 23));

        employee.setVacationBookings(vacationBookings);

        Map<PhoneType, String> phoneNumbers = new LinkedHashMap<PhoneType, String>();
        phoneNumbers.put(PhoneType.HOME, "000000000");
        phoneNumbers.put(PhoneType.MOBILE, "111111111");
        phoneNumbers.put(PhoneType.WORK, "222222222");

        employee.setPhoneNumbers(phoneNumbers);

        Department department = new Department();
        department.setName("BASA DEPARTMENT");

        Map<String, Employee> employeeMap = new HashMap<String, Employee>();
        employeeMap.put("09321ZZ", employee);
        department.setEmployeesByCubicle(employeeMap);

        Map<Employee, Integer> senioritiesMap = new HashMap<Employee, Integer>();
        senioritiesMap.put(employee, 13);
        department.setSeniorities(senioritiesMap);

        employee.setTeam(new Team());

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(department);
        transaction.commit();
    }
}
