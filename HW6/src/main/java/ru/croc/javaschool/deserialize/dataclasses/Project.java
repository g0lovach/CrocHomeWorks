package ru.croc.javaschool.deserialize.dataclasses;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Проект
 */
public class Project {
    /**
     * Название проекта
     */
    @JacksonXmlProperty(localName = "title")
   private String title;
    /**
     * Описание проекта
     */
    @JacksonXmlProperty(localName = "description")
   private String description;

    /**
     * Список менеджеров проекта
     */
    @JacksonXmlElementWrapper(localName = "managers")
    @JacksonXmlProperty(localName = "manager")
     private List<Manager> managers;

    /**
     * @return список менеджеров проекта
     */
    public List<Manager> getManagers() {
        return managers;
    }

    /**
     * Замена списка менеджеров
     * @param managers - новый список менеджеров
     */
    public void setManagers(List<Manager> managers) {
        this.managers = managers;
    }

    /**
     * Конструктор
     * @param title - название
     * @param description - описания
     * @param managers - список менеджеров
     */
    public Project(String title, String description, List<Manager> managers) {
        this.title = title;
        this.description = description;
        this.managers = managers;
    }

    /**
     * @return название проекта
     */
    public String getTitle() {
        return title;
    }

    /**
     * Замена названия
     * @param title - новое название
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return Описание проекта
     */
    public String getDescription() {
        return description;
    }

    /**
     * Замена описания
     * @param description - новое описание
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * Конструктор
     */
    public Project() {
    }

    /**
     * @return строковое представление проекта
     */
    @Override
    public String toString() {
        return "Project{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", managers=" + managers +
                '}';
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
        Project project = (Project) o;
        return Objects.equals(title, project.title) && Objects.equals(description, project.description) && Objects.equals(managers, project.managers);
    }
    /**
     * @return хэш объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(title, description, managers);
    }
}
