package ru.croc.javaschool.deserialize.dataclasses;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Список проектов
 */
@JacksonXmlRootElement
public class Projects {
    /**
     * Проекты
     */
    @JacksonXmlElementWrapper(localName = "projects", useWrapping = false)
    @JacksonXmlProperty(localName = "project")
    private List<Project> projects;

    /**
     * @return проекты
     */
    public List<Project> getProjects() {
        return projects;
    }

    /**
     * Замена списка проектов
     * @param projects - новый список проектов
     */
    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    /**
     * Конструктор
     * @param projects - проекты
     */
    public Projects(List<Project> projects) {
        this.projects = projects;
    }

    /**
     * Конструктор
     */
    public Projects() {
    }

    /**
     * @return строковое представление списка проектов
     */
    @Override
    public String toString() {
        return "projects{" + projects +
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
        Projects projects1 = (Projects) o;
        return Objects.equals(projects, projects1.projects);
    }
    /**
     * @return хэш объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(projects);
    }
}
