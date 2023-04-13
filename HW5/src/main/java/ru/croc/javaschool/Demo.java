package ru.croc.javaschool;

import ru.croc.javaschool.execeptions.DuplicateElementException;
import ru.croc.javaschool.execeptions.IncorrectCodeException;
import ru.croc.javaschool.execeptions.IncorrectIdException;

import java.io.*;

/**
 * Демонстрационный класс
 */
public class Demo {
    /**
     * Точка входа в систему
     * @param args
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws IncorrectIdException
     * @throws IncorrectCodeException
     * @throws DuplicateElementException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException, IncorrectIdException, IncorrectCodeException, DuplicateElementException {
      TaskManager taskManager = new TaskManager();
    }
}
