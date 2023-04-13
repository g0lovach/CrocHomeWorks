package ru.croc.javaschool.dataclasses;

import java.io.Serializable;
import java.util.Objects;


/**
 * Класс, описывающий исполнителя
 */
public class Executor implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * id исполнителя
     */
    private final int id;
    /**
     * Имя исполнителя
     */
    private String name;


    /**
     * конструктор
     * @param id - id
     * @param name - имя
     */
    public Executor(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * @return Строковое представление исполнителя
     */
    @Override
    public String toString() {
        return "Executor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


    /**
     * Получение id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Получение имени
     * @return имя
     */
    public String getName() {
        return name;
    }

    /**
     * Установка имени
     * @param name - имя
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Метод проверки на эквивалентность
     * @param o - сравниваемый объект
     * @return - результат проверки
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Executor executor = (Executor) o;
        return id == executor.id;
    }

    /**
     * @return хэш-код объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
