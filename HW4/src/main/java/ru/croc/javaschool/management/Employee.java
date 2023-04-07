package ru.croc.javaschool.management;

import java.util.Objects;

/**
 * класс, описывающий сотрудника компании
 */
public class Employee implements Comparable<Employee>{

    /**
     * id внутри компании
     */
    private final int id;
    /**
     * имя
     */
    private final String name;
    /**
     * управляющий для этого сотрудника
     */
    private final Employee manager;

    /**
     * Конструктор
     * @param id - id внутри компании
     * @param name - имя
     * @param manager - управляющий для этого сотрудника
     */
    public Employee(int id, String name, Employee manager) {
        this.id = id;
        this.name = name;
        this.manager = manager;
    }

    /**
     * получить управляющего
     * @return - управляющий
     */
     Employee getManager() {
        return manager;
    }

    /**
     * Сравнение объектов
     * @param o the object to be compared.
     * @return  - результат сравнения
     */
    @Override
    public int compareTo(Employee o) {
        return this.name.compareTo(o.name);
    }

    /**
     * @return Строковое представление объекта
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Проверка на эквивалетность
     * @param o - сравнинваемый объект
     * @return - результат проверки на эквивалентность
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && name.equals(employee.name) && Objects.equals(manager, employee.manager);
    }

    /**
     * @return хэш-код объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, manager);
    }
}
