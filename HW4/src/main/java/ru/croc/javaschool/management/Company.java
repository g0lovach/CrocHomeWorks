package ru.croc.javaschool.management;

import java.util.*;

/**
 * Класс описывающий компанию
 */
public class Company {

    /**
     * id  компании
     */
    private final int id;

    /**
     * список сотрудников компании
     */
    private List<Employee> employees;

    /**
     * Конструктор
     * @param id - id компании
     */
     public Company(int id) {
        this.id = id;
        employees = new LinkedList<>();
    }

    /**
     * Добавления сотрудника в список сотрудников компании
     * @param employee - новый сотрудник
     */
    public void addEmployee(Employee employee){
        employees.add(employee);
    }


    /**
     * Поиск директора компании (единственного соткрудника, для которого не задан менеджер)
     * @return директор
     */
    Employee getDirector(){
        for (Employee employee: employees){
            if (employee.getManager()==null){
                return employee;
            }
        }
        return null;
    }

    /**
     * @return строковое представление
     */
    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", employees=" + employees +
                '}';
    }

    /**
     * Сравнение
     * @param o - сравниваемый объект
     * @return - результат сравнения
     */
    @Override
    public boolean equals(Object o){
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Company company = (Company) o;
        return Arrays.equals(employees.toArray(), company.employees.toArray()) && id == company.id;
    }

    /**
     * @return хэш объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, employees);
    }
}
