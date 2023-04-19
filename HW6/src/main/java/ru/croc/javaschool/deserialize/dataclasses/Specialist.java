package ru.croc.javaschool.deserialize.dataclasses;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.Objects;


/**
 * Спициалист
 */
public class Specialist {
    /**
     * Имя специалиста
     */
    @JacksonXmlProperty
     private String name;

    /**
     * @return строковое представление специалиста
     */
    @Override
    public String toString() {
        return "Specialist{" +
                "name='" + name + '\'' +
                '}';
    }

    public Specialist() {
    }

    /**
     * @return имя специалиста
     */
    public String getName() {
        return name;
    }

    /**
     * Конструктор
     * @param name - имя
     */
    public Specialist(String name) {
        this.name = name;
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
        Specialist that = (Specialist) o;
        return Objects.equals(name, that.name);
    }
    /**
     * @return хэш объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
