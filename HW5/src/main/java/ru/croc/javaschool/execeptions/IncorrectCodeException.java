package ru.croc.javaschool.execeptions;

/**
 * Класс-исключение попытки обратиться к несуществующей карточке
 */
public class IncorrectCodeException extends Exception {
    /**
     * Конструктор
     */
    public IncorrectCodeException() {
        super("Карточка с таким кодом не найдена!");
    }
}
