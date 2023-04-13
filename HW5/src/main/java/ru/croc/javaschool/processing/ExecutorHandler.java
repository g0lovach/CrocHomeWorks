package ru.croc.javaschool.processing;

import ru.croc.javaschool.dataclasses.Executor;
import ru.croc.javaschool.execeptions.IncorrectIdException;
import ru.croc.javaschool.tools.PathTool;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Класс, предназначенный для взаимодействия с файлом, содержащим информацию о исполнителях
 */
public class ExecutorHandler implements AutoCloseable {


    /**
     * Установка дефолтного состояния
     * @throws IOException
     */
    public static void setDefaultExecutorState() throws IOException {
        writeExecutorsToFile(new ArrayList<>());
    }

    /**
     * Получение исполнителей из файла
     * @return - список исполнителей
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public static ArrayList<Executor> getExecutorsFromFile() throws IOException {
        PathTool.checkDirExistence();
        ArrayList<Executor> res = new ArrayList<>();
          try {
            File file = new File("src/main/resources/datafiles/executors.bin");

            InputStream is = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(is);
            res =   (ArrayList<Executor>) ois.readObject();
              ois.close();

        }
        catch (EOFException | ClassNotFoundException classNotFoundException){
            ExecutorHandler.setDefaultExecutorState();


        } catch (IOException ioException){
              System.out.println("Ошибка работы с файлом executors.bin! Будет создан новый пустой файл, а старый удален.");
              File file  = new File("src/main/resources/datafiles/executors.bin");
              if(file.exists()){
                  file.delete();

              }
              file.createNewFile();
          }
        return res;

    }

    /**
     * Удаление исполнителя
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws IncorrectIdException
     * @throws InputMismatchException
     */
    public static void deleteExecutor() throws IOException, ClassNotFoundException, IncorrectIdException, InputMismatchException {
        while(true) {

            ArrayList<Executor> executors = getExecutorsFromFile();
            if(executors.size()==0){
                System.out.println("На данный момент в системе ни одного исполнителя не зафиксировано!");
                return;
            }

            System.out.println("Введите id исполнителя, которого хотели бы удалить: ");
            Scanner in = new Scanner(System.in);

            try {
                int id = in.nextInt();
                in.nextLine();
                Executor tmp = findExecutorById(id);
                boolean found = false;
                if (tmp != null) {
                    executors.remove(tmp);
                    found = true;
                }

                if (found) {
                    System.out.println("Уверены, что хотите удалить (введите y, если да и что угодно, если нет)?");
                    if(!in.nextLine().equals("y")){
                        return;
                    }
                    writeExecutorsToFile(executors);
                    TaskHandler.cascadeDeleteExecutor(id);
                    break;
                } else {
                    throw new IncorrectIdException();
                }
            } catch (IncorrectIdException incorrectIdException){
                System.out.println(incorrectIdException.getMessage());
            }
            catch (InputMismatchException inputMismatchException){
                System.out.println("Недопустимый формат id! Введите число");
            }
        }

    }

    /**
     * Добавление исполнителя
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void addExecutor() throws IOException, ClassNotFoundException{

        List<Executor> executors = getExecutorsFromFile();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Name: ");
        String name = scanner.nextLine();
        Executor executor;
        if(executors.size()!=0) {
             executor = new Executor(executors.get(executors.size() - 1).getId() + 1, name);
        }
        else{
             executor = new Executor(1, name);
        }
        executors.add(executor);

        writeExecutorsToFile(executors);


    }

    /**
     * Вывод исполнителей на экран
     * @throws IOException
     */
    public static void printExecutors() throws IOException {
        ArrayList<Executor> executors = getExecutorsFromFile();
        if(executors.size()==0){
            System.out.println("На данный момент исполнители не зарегистрированы!");
        }
        for (Executor executor: executors){
            System.out.println(executor);
        }
    }

    /**
     * Изменение исполнителя
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws IncorrectIdException
     * @throws InputMismatchException
     */
    public static void editExecutor() throws IOException, ClassNotFoundException, IncorrectIdException, InputMismatchException {
        List<Executor> executors = getExecutorsFromFile();
        if(executors.size()==0){
            System.out.println("На данный момент в системе ни одного исполнителя не зафиксировано!");
            return;
        }
        while(true) {
            Scanner in = new Scanner(System.in);
            System.out.println("Введите id исполнителя, которого хотите изменить: ");
            try {
            int id = in.nextInt();
            in.nextLine();
                for (Executor executor : executors) {
                    if (executor.getId() == id) {
                        System.out.println("Введите новое имя исполнителя: ");
                        executor.setName(in.nextLine());
                        writeExecutorsToFile(executors);
                        TaskHandler.cascadeEditExecutor(executor.getId(), executor.getName());
                        return;
                    }
                }
                throw new IncorrectIdException();
            }
            catch (IncorrectIdException incorrectIdException){
                System.out.println(incorrectIdException.getMessage());
            }
            catch (InputMismatchException inputMismatchException){
                System.out.println("Недопустимый формат id! Введите число");
            }
        }




    }

    /**
     * Запись исплнителей в файл
     * @param executors - исполнители
     * @throws IOException
     */
    private static void writeExecutorsToFile(List<Executor> executors) throws IOException {
        PathTool.checkDirExistence();
        try {
            ObjectOutputStream objectOutputStream =
                    new ObjectOutputStream(new FileOutputStream("src/main/resources/datafiles/executors.bin"));
            objectOutputStream.writeObject(executors);
            objectOutputStream.close();

        }
        catch (IOException ioException){
            System.out.println("Не удается выполнить запись в executors.file! Изменения не внесены. Будет создан новый пустой файл");
            File file  = new File("src/main/resources/datafiles/executors.bin");
            if(file.exists()){
                file.delete();
            }
            file.createNewFile();
        }


    }

    /**
     * Поиск в файле исполнителя с заданным id
     * @param id - id искомого исполнителя
     * @return - исполнитель
     * @throws IOException
     */
    public static Executor findExecutorById(int id) throws IOException {

        ArrayList<Executor> executors = getExecutorsFromFile();

        for(Executor executor: executors){
            if(executor.getId() == id){
                return executor;
            }
        }
        return null;
    }


    /**
     * Метод закрытия, переопределенный по требованию интерфейса AutoCloseable
     * @throws Exception
     */
    @Override
    public void close() throws Exception {

    }
}
