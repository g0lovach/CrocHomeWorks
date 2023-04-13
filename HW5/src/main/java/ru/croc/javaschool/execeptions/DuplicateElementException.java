package ru.croc.javaschool.execeptions;

/**
 * Класс-исключение дублирования элементов
 */
public class DuplicateElementException extends Exception {
    /**
     * Конструктор
     */
    public DuplicateElementException() {

        super("Элемент с таким кодом/id уже есть в системе!");
    }
}
