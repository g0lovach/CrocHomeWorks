package ru.croc.javaschool.serialize.dataclasses;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.Objects;

/**
 * Участие человека в проекте
 */
public class Participation {
    /**
     * Название проекта
     */
    @XmlAttribute(name = "title")
    private final String projectName;
    /**
     * Роль человека в проекте
     */
    @XmlElement(name = "role")
    private final String role;
    /**
     * Имя менеджера
     */
    @XmlElement(name = "manager")
    private final String managerName;

    /**
     * Конструктор
     * @param projectName - название проекта
     * @param role - роль в проекте
     * @param managerName - имя менеджера
     */
    public Participation(String projectName, String role, String managerName) {
        this.projectName = projectName;
        this.role = role;
        this.managerName = managerName;
    }

    /**
     * @return строковое представление
     */
    @Override
    public String toString() {
        return "Participation{" +
                "projectName='" + projectName + '\'' +
                ", role='" + role + '\'' +
                ", managerName='" + managerName + '\'' +
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
        Participation that = (Participation) o;
        return Objects.equals(projectName, that.projectName) && Objects.equals(role, that.role) && Objects.equals(managerName, that.managerName);
    }
    /**
     * @return хэш объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(projectName, role, managerName);
    }
}
