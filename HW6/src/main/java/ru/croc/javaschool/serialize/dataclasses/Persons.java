package ru.croc.javaschool.serialize.dataclasses;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Список людей(сотрудников)
 */
@XmlRootElement(name = "people")

public class Persons {
    /**
     * Сотрудники
     */
        @XmlElement(name = "person", required = false)
    private List<Person> people;

    /**
     * Конструктор
     */
    public Persons() {
    }

    /**
     * Замена сотрудников
     * @param people - новые сотрудники
     */
    public void setPeople(List<Person> people) {
        this.people = people;
    }

    /**
     * @return Строковое представление
     */
    @Override
    public String toString() {
        return Arrays.toString(people.toArray());
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
        Persons persons = (Persons) o;
        return Objects.equals(people, persons.people);
    }

    /**
     * @return хэш объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(people);
    }
}
