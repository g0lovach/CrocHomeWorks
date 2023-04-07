import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.management.Company;
import ru.croc.javaschool.management.Employee;
import ru.croc.javaschool.management.ManagementHandler;

import java.util.*;

/**
 * Тестовый класс для {@link ManagementHandler}
 */
public class ManagementHandlerTest {


    /**
     * Тестовый метод для {@link ManagementHandler#getRankedListOfDirectors(List)}
     */
@Test
    public void testGetRankedListOFDirectors(){
    //Создаем сотрудников и заполняем список
    Employee employee1 = new Employee(1,"Dmitriy",null);
    Employee employee2 = new Employee(2,"Ilya",employee1);
       Employee employee3 = new Employee(3,"Alex",employee1);
    Employee employee4 = new Employee(1,"Vladimir",null);
    Employee employee5 = new Employee(2,"Artem",employee4);
    Employee employee6 = new Employee(3,"Zorik",employee5);
    List<Employee> employees = new ArrayList<>();
    employees.add(employee1);
    employees.add(employee2);
    employees.add(employee3);
    employees.add(employee4);
    employees.add(employee5);
    employees.add(employee6);

    //Определяем данные для сверки
    Map<Employee,Integer> actual = ManagementHandler.getRankedListOfDirectors(employees);
    Map<Employee, Integer> expected = new LinkedHashMap<>();
    expected.put(employee1, 2);
    expected.put(employee4, 2);
    expected.put(employee5, 1);

    //Проверка
    Assertions.assertArrayEquals(expected.entrySet().toArray(), actual.entrySet().toArray());


}

    /**
     * Тестовый метод для {@link ManagementHandler#getEmployeesSeparatedForCompanies(List)}
     */
@Test
    public void testGetEmployeesSeparatedForCompanies(){
    //Создаем сотрудников и заполняем список
    Employee employee1 = new Employee(1,"Dmitriy",null);
    Employee employee2 = new Employee(2,"Ilya",employee1);
    Employee employee3 = new Employee(3,"Alex",employee2);
    Employee employee4 = new Employee(1,"Vladimir",null);
    Employee employee5 = new Employee(2,"Artem",employee4);
    Employee employee6 = new Employee(3,"Zorik",employee5);
    List<Employee> employees = new ArrayList<>();
    employees.add(employee1);
    employees.add(employee2);
    employees.add(employee4);
    employees.add(employee5);
    employees.add(employee6);
    employees.add(employee3);

    //Определяем данные для сверки
    Company company1 = new Company(1);
    company1.addEmployee(employee1);
    company1.addEmployee(employee2);
    company1.addEmployee(employee3);

    Company company2 = new Company(2);
    company2.addEmployee(employee4);
    company2.addEmployee(employee5);
    company2.addEmployee(employee6);

    List<Company> expected = new LinkedList<>();
    expected.add(company1);
    expected.add(company2);

    //Результат работы тестируемого метода
    List<Company> actual = ManagementHandler.getEmployeesSeparatedForCompanies(employees);

    //Проверка
    Assertions.assertEquals(expected, actual);






}

}
