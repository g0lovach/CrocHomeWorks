package ru.croc.javaschool.serialize.dataclasses;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Человек (сотрудник)
 */
public class Person {
    /**
     * Имя сотрудника
     */
    @XmlElement(name = "name")

    private final String name;

    /**
     * Описание его участий в проектах
     */
    @XmlElementWrapper(name = "projects")
    @XmlElement(name = "project")
    private List<Participation> participations;

    /**
     * Конструктор
     * @param name - имя
     */
    public Person(String name) {
        this.name = name;
        this.participations = new ArrayList<>();
    }

    /**
     * @return строковое представление
     */
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", participations=" + participations +
                '}';
    }

    /**
     * @return имя
     */
    public String getName() {
        return name;
    }

    /**
     * Добавить участие
     * @param participation - участие
     */
    public void addParticipation(Participation participation){
        this.participations.add(participation);
    }
    /**
     * Проверка на эквивалентность
     * @param o - сравниваемый объект
     * @return - результат
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(participations, person.participations);
    }
    /**
     * @return хэш объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, participations);
    }
}
