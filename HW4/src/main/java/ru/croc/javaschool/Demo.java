package ru.croc.javaschool;

import ru.croc.javaschool.management.Employee;
import ru.croc.javaschool.management.ManagementHandler;
import ru.croc.javaschool.ranking.Author;
import ru.croc.javaschool.ranking.RankingHandler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Demo {
    public static void main(String[] args) {
        //Создаем сотрудников и заполняем список
        Employee employee1 = new Employee(1,"Dmitriy",null);
        Employee employee2 = new Employee(2,"Ilya",employee1);
        Employee employee3 = new Employee(3,"Alex",employee1);
        Employee employee4 = new Employee(1,"Vladimir",null);
        Employee employee5 = new Employee(2,"Artem",employee4);
        Employee employee6 = new Employee(3,"Zorick",employee5);
        List<Employee> test = new ArrayList<>();
        test.add(employee1);
        test.add(employee2);
        test.add(employee3);
        test.add(employee4);
        test.add(employee5);
        test.add(employee6);

        //Компании, по которым распределены сотрудники
        System.out.println(ManagementHandler.getEmployeesSeparatedForCompanies(test));

        //Ранжированный список руководителей
        System.out.println(ManagementHandler.getRankedListOfDirectors(test));



        //Создаем записи и заполняем список записей
        String record1 = "word excel;Kovalenko; WoRd,. !?.,-Excel point dir";
        String record2 = "java;Akopyan; python php java java java java java c++ qwe qwe";
        String record3 = "comp comp file file;Sklyar; com com com comp comp comp";
        List<String> records = new LinkedList<>();
        records.add(record1);
        records.add(record2);
        records.add(record3);
        System.out.println();


        //Ранжирование по встречаемости слов из названия статьи в тексте статьи
        for(Map.Entry<Author,Double> entry: RankingHandler.makeRankingOfAuthors(records).entrySet()){
            System.out.println(entry.getKey() + " = "+ entry.getValue());
        }

        //Меняем входные данные
         record3 = "comp comp file file;Sklyar; com com com com com com com com com comp comp comp";
        System.out.println();
        records.clear();
        records.add(record1);
        records.add(record3);

        //Ранжирование по встречаемости слов из названия статьи в тексте статьи
        for(Map.Entry<Author,Double> entry: RankingHandler.makeRankingOfAuthors(records).entrySet()){
            System.out.println(entry.getKey() + " = "+ entry.getValue());
        }


    }
}