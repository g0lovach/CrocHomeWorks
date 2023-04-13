package ru.croc.javaschool.execeptions;
/**
 * Класс-исключение попытки обратиться к несуществующей карточке
 */
public class IncorrectIdException extends Exception{
    /**
     * Конструктор
     */
    public IncorrectIdException() {
        super("Исполнитель с таким id не найден!");
    }
}
