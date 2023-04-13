package ru.croc.javaschool;

import ru.croc.javaschool.execeptions.DuplicateElementException;
import ru.croc.javaschool.execeptions.IncorrectCodeException;
import ru.croc.javaschool.execeptions.IncorrectIdException;
import ru.croc.javaschool.processing.ExecutorHandler;
import ru.croc.javaschool.processing.TaskHandler;
import ru.croc.javaschool.tools.PathTool;

import java.io.*;

import java.util.Scanner;


/**
 * Класс, описывающий систему управления карточками
 */
public class TaskManager {

    /**
     * Конструктор
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws IncorrectIdException
     * @throws IncorrectCodeException
     * @throws DuplicateElementException
     */
    public TaskManager() throws IOException, ClassNotFoundException, IncorrectIdException, IncorrectCodeException, DuplicateElementException {
        PathTool.checkDirExistence();
        while(true) {
            Scanner in = new Scanner(System.in);
            System.out.println("======================================================================\n" +
                    "1. Вывести все карточки; 2. Вывести всех исполнителей;\n" +
                    "3. Добавить карточку; 4. Добавить исполнителя;\n" +
                    "5. Удалить карточку; 6. Удалить исполнителя;\n" +
                    "7. Редактировать карточку; 8. Редактировать исполнителя;\n" +
                    "9. Выйти;\n" +
                    "======================================================================\n");
            int num = in.nextInt();

            if(num == 1){
                TaskHandler.printTasks();
            } else if (num == 2) {
                ExecutorHandler.printExecutors();

            } else if (num == 3) {
                TaskHandler.addTask();
            }
            else if (num == 4) {
                ExecutorHandler.addExecutor();
            }
            else if (num == 5) {
                TaskHandler.deleteTask();
            }
            else if (num == 6) {
                ExecutorHandler.deleteExecutor();
            }
            else if (num == 7) {
                TaskHandler.editTask();
            }
            else if (num == 8) {
                ExecutorHandler.editExecutor();
            }
            else if (num == 9) {
                break;
            }


        }

    }

}
