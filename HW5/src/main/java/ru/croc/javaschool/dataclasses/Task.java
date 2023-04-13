package ru.croc.javaschool.dataclasses;


import java.io.Serializable;
import java.util.Objects;

/**
 * класс, описывающий задачи
 */
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Код задачи
     */
    private String code;
    /**
     * Название задачи
     */
    private String name;
    /**
     * Описание задачи
     */
    private String description;
    /**
     * Исполнитель задачи
     */
    private Executor executor;
    /**
     * Статус задачи
     */
    private String status;

    /**
     * Конструктор
     * @param code - код
     * @param name- название
     * @param description - описание
     * @param executor - исполнитель
     * @param status - статус
     */
    public Task(String code, String name, String description, Executor executor, String status) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.executor = executor;
        this.status = status;
    }


    /**
     * Получение кода задачи
     * @return код
     */
    public String getCode() {
        return code;
    }

    /**Установка кода задачи
     * @param code  - код
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Установка названия задачи
     * @param name - название
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Установка описания
     * @param description - описание
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Получение исполнителя
     * @return - исполнитель
     */
    public Executor getExecutor() {
        return executor;
    }

    /**
     * Установка исполнителя
     * @param executor - исполнитель
     */
    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    /**
     * Установка статуса
     * @param status - статус
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return Строковое представление объекта
     */
    @Override
    public String toString() {
        return "Task{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", executor=" + executor +
                ", status='" + status + '\'' +
                '}';
    }

    /**
     * Проверка на эквивалентность
     * @param o - сравниваемый объект
     * @return - результат сравнения
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(code, task.code);
    }

    /**
     *
     * @return хэш-код объекта
     *
     */
    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
