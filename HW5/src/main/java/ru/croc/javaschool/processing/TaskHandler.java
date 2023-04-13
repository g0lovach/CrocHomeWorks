package ru.croc.javaschool.processing;

import ru.croc.javaschool.dataclasses.Executor;
import ru.croc.javaschool.dataclasses.Task;
import ru.croc.javaschool.execeptions.DuplicateElementException;
import ru.croc.javaschool.execeptions.IncorrectCodeException;
import ru.croc.javaschool.execeptions.IncorrectIdException;
import ru.croc.javaschool.tools.PathTool;

import java.io.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Класс, описывающий взаимодействия с файлом, содержащим информацию о задачах
 */
public class TaskHandler implements AutoCloseable {


    /**
     * Удаление задачи
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws IncorrectCodeException
     */
    public static void deleteTask() throws IOException, ClassNotFoundException, IncorrectCodeException {
        List<Task> tasks = getTasksFromFile();
        if(tasks.size()==0){
            System.out.println("На данный момент в системе карточек не зафиксировано!");
            return;
        }
        while (true) {

            System.out.println("Введите код карточки, которую хотели бы удалить: ");
            Scanner in = new Scanner(System.in);
            String code = in.nextLine();

            boolean found = false;
            try {
                for (Task task : tasks) {
                    if (task.getCode().equals(code)) {

                        tasks.remove(task);
                        found = true;
                        break;

                    }
                }
                if (found) {
                    System.out.println("Уверены, что хотите удалить (введите y, если да и что угодно, если нет)?");
                    if(!in.nextLine().equals("y")){
                        return;
                    }
                    writeTasksToFile(tasks);
                    break;
                } else {
                    throw new IncorrectCodeException();
                }
            }
            catch (IncorrectCodeException incorrectCodeException){
                System.out.println(incorrectCodeException.getMessage());

            }
        }

    }

    /**
     * Каскадное удаление из задач ранее удаленного исполнителя
     * @param id - id удаленного исполнителя
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void cascadeDeleteExecutor(int id) throws IOException, ClassNotFoundException {
        List<Task> tasks = getTasksFromFile();
        boolean edited = false;
        if(tasks.size()!=0) {
            for (Task task : tasks) {
                if(task.getExecutor() == null){
                    continue;
                }
                if (task.getExecutor().getId() == id) {
                    edited = true;
                    task.setExecutor(null);
                }
            }
            if (edited) {
                writeTasksToFile(tasks);
            }
        }
    }

    /**
     * Каскадное изменение информации об исполнителе в задаче после изменения заданного исполнителя
     * @param id - id заданного исполнителя
     * @param name - Новое имя исполнителя
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void cascadeEditExecutor(int id, String name) throws IOException, ClassNotFoundException {
        List<Task> tasks = getTasksFromFile();
        boolean edited = false;
        if(tasks.size()!=0) {
            for (Task task : tasks) {
                if(task.getExecutor()==null){
                    continue;
                }
                if (task.getExecutor().getId() == id) {
                    edited = true;
                    task.getExecutor().setName(name);
                }
            }
            if (edited) {
                writeTasksToFile(tasks);
            }
        }
    }


    /**
     * Установка дефолтного состония файла
     */
    public static void setDefaultTaskState() {


        writeTasksToFile(new ArrayList<>());


    }

    /**
     * Получение списка задач из файла
     * @return список задач
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
    public static ArrayList<Task> getTasksFromFile() throws IOException, ClassNotFoundException {
        PathTool.checkDirExistence();
        ArrayList<Task> res = new ArrayList<>();
        try {
            File file = new File("src/main/resources/datafiles/tasks.bin");

            InputStream is = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(is);
            res = (ArrayList<Task>) ois.readObject();
            ois.close();

        }
        catch (EOFException eof){
            TaskHandler.setDefaultTaskState();
        }
        catch (IOException ioException){
        System.out.println("Ошибка работы с файлом tasks.bin! Будет создан новый пустой файл, а старый удален.");
        File file  = new File("src/main/resources/datafiles/tasks.bin");
        if(file.exists()){
            file.delete();

        }
        file.createNewFile();
    }
        return res;


    }


    /**
     * Добавление задачи
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws DuplicateElementException
     * @throws IncorrectIdException
     */
    public static void addTask() throws IOException, ClassNotFoundException, DuplicateElementException, IncorrectIdException {

        List<Task> tasks = getTasksFromFile();
        List<Executor> executors = ExecutorHandler.getExecutorsFromFile();
        if(executors.size()==0){
            System.out.println("Невозможно создать карточку, т.к. в системе не определено ни одного исполнителя!");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        String code;
        while(true) {
            System.out.print("Code: ");
            code = scanner.nextLine();
            try {
                Task tmp = findTaskByCode(code);
                if (tmp != null) {
                    throw new DuplicateElementException();
                }
                break;
            } catch (DuplicateElementException duplicateElementException) {
                System.out.println(duplicateElementException.getMessage());
            }
        }
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        Executor executorForTask;
        while(true) {

            System.out.print("Executor's ID: ");
            try {
            int executorId = scanner.nextInt();

                 executorForTask = ExecutorHandler.findExecutorById(executorId);
                if (executorForTask == null) {
                    throw new IncorrectIdException();
                }
                break;
            } catch (IncorrectIdException incorrectIdException) {
                System.out.println(incorrectIdException.getMessage());
            }catch (InputMismatchException inputMismatchException){
                System.out.println("Недопустимый формат id! Введите число");
                scanner.nextLine();
            }
        }
        scanner.nextLine();
        System.out.print("Status: ");
        String status = scanner.nextLine();
        Task task = new Task(code, name, description, executorForTask, status);
        tasks.add(task);

        writeTasksToFile(tasks);


    }

    /**
     * вывод на экран всех задач из файла
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void printTasks() throws IOException, ClassNotFoundException {
        List<Task> tasks = getTasksFromFile();
        if(tasks.size()==0){
            System.out.println("На данный момент карточек не зафиксировано!");
        }
        for (Task task: tasks){
            System.out.println(task);
        }
    }

    /**
     * Изменение задачи
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws IncorrectCodeException
     */
    public static void editTask() throws IOException, ClassNotFoundException, IncorrectCodeException {
        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = TaskHandler.getTasksFromFile();
        if(tasks.size()==0){
            System.out.println("На данный момент в системе карточек не зафиксировано!");
            return;
        }
        Task taskToEdit = null;
        while(true) {
            System.out.println("Введите код карточки, которую хотите изменить: ");
            String code = scanner.nextLine();
            for (Task task : tasks) {
                if (task.getCode().equals(code)) {
                    System.out.println(task);
                    taskToEdit = task;
                    break;
                }
            }
            try {
                if (taskToEdit == null) {
                    throw new IncorrectCodeException();
                }
                break;
            } catch (IncorrectCodeException incorrectCodeException) {
                System.out.println(incorrectCodeException.getMessage());
            }
        }
        String response = "";
        while(!response.equals("y") && !response.equals("n")&& !response.equals("q")){
            System.out.println("Изменить код? (y/n/q)");
            response = scanner.nextLine();
        }
        if(response.equals("y")){
            while(true){
                System.out.println("Введите новый код: ");
                String newCode = scanner.nextLine();
                Task tmp = findTaskByCode(newCode);
                try {
                    if (tmp != null) {
                        if (tmp.getCode().equals(newCode)) {
                            throw new DuplicateElementException();
                        }

                    }
                }
                catch (DuplicateElementException duplicateElementException){
                    System.out.println(duplicateElementException.getMessage());
                    continue;
                }
                taskToEdit.setCode(newCode);
                break;

            }
        }
        if(response.equals("q")){
            return;
        }

        response = "";
        while(!response.equals("y") && !response.equals("n")&& !response.equals("q")){
            System.out.println("Изменить название? (y/n/q)");
            response = scanner.nextLine();
        }
        if(response.equals("y")){
                System.out.println("Введите новое название: ");
                String newName = scanner.nextLine();
                taskToEdit.setName(newName);
        }
        if(response.equals("q")){
            return;
        }

        response = "";
        while(!response.equals("y") && !response.equals("n")&& !response.equals("q")){
            System.out.println("Изменить описание? (y/n/q)");
            response = scanner.nextLine();
        }
        if(response.equals("y")){
            System.out.println("Введите новое описание: ");
            String newDescription = scanner.nextLine();
            taskToEdit.setDescription(newDescription);
        }
        if(response.equals("q")){
            return;
        }

        response = "";
        while(!response.equals("y") && !response.equals("n")&& !response.equals("q")){
            System.out.println("Изменить исполнителя? (y/n/q)");
            response = scanner.nextLine();
        }
        if(response.equals("y")){
            while(true){
                System.out.println("Введите id нового исполнителя (или 0, если хотите выйти): ");
                try {
                int newExecutor = scanner.nextInt();
                scanner.nextLine();
                if(newExecutor == 0){
                    return;
                }
                Executor executor = ExecutorHandler.findExecutorById(newExecutor);

                boolean found = executor!=null;

                    if (!found) {
                        throw new IncorrectIdException();
                    }
                    taskToEdit.setExecutor(executor);
                    break;
                }
                catch (IncorrectIdException incorrectIdException){
                    System.out.println(incorrectIdException.getMessage());
                }
                catch (InputMismatchException inputMismatchException){
                    System.out.println("Недопустимый формат id! Введите число");
                    scanner.nextLine();
                }

            }
        }
        if(response.equals("q")){
            return;
        }

        response = "";
        while(!response.equals("y") && !response.equals("n")&& !response.equals("q")){
            System.out.println("Изменить статус? (y/n/q)");
            response = scanner.nextLine();
        }
        if(response.equals("y")){
            System.out.println("Введите новый статус: ");
            String newStatus = scanner.nextLine();
            taskToEdit.setStatus(newStatus);
        }
        if(response.equals("q")){
            return;
        }


        writeTasksToFile(tasks);



    }

    /**
     * Запись задач в файл
     * @param tasks - задачи
     */
    private static void writeTasksToFile(List<Task> tasks){
        PathTool.checkDirExistence();
        try {
            ObjectOutputStream objectOutputStream =
                    new ObjectOutputStream(new FileOutputStream("src/main/resources/datafiles/tasks.bin"));
            objectOutputStream.writeObject(tasks);
            objectOutputStream.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    /**
     * Поиск задачи в файле по коду
     * @param code - код искомой задачи
     * @return - задача
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private static Task findTaskByCode(String code) throws IOException, ClassNotFoundException {

        ArrayList<Task> tasks = getTasksFromFile();

        for (Task task: tasks){
            if(task.getCode().equals(code)){
                return task;
            }
        }

    return null;
    }


    /**
     * Метод, переопределенный по требованию интерфейса AutoCloseable
     * @throws Exception
     */
    @Override
    public void close() throws Exception {

    }
}
