package ru.croc.javaschool.deserialize.dataclasses;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;
import java.util.Objects;

/**
 * Менеджер проекта
 */
public class Manager {
    /**
     * Имя менеджера
     */
    @JacksonXmlProperty
    private String name;
    /**
     * Список специалистов в подчинении у менеджера
     */
   @JacksonXmlElementWrapper(localName = "specialists")
            @JacksonXmlProperty(localName = "specialist")
   private List<Specialist> specialists;

    /**
     * @return строковое представление менеджера
     */
    @Override
    public String toString() {
        return "Manager{" +
                "name='" + name + '\'' +
                ", specialists=" + specialists +
                '}';
    }

    /**
     * Конструктор
     * @param name - имя
     * @param specialists - починенные
     */
    public Manager(String name, List<Specialist> specialists) {
        this.name = name;
        this.specialists = specialists;
    }

    public Manager() {
    }

    /**
     * @return имя менеджера
     */
    public String getName() {
        return name;
    }

    /**
     * @return получение списка подчиненных
     */
    public List<Specialist> getSpecialists() {
        return specialists;
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
        Manager manager = (Manager) o;
        return Objects.equals(name, manager.name) && Objects.equals(specialists, manager.specialists);
    }
    /**
     * @return хэш объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, specialists);
    }
}
