package ro.tekin.jpa;

import ro.tekin.jpa.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.*;

/**
 * Created by tekin.omer on 9/12/2015.
 */
public class EntityManagerEx1 {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("exam");
        EntityManager em = entityManagerFactory.createEntityManager();

        method1(em);
        method2(em);
    }

    private static void method2(EntityManager em) {
        List<Employee> employees = em.createQuery("select e from Employee e", Employee.class).getResultList();
        List<Department> departments = em.createQuery("select e from Department e", Department.class).getResultList();
        List<Team> teams = em.createQuery("select e from Team e", Team.class).getResultList();
        System.out.println(employees.size());
    }

    private static void method1(EntityManager em) {
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