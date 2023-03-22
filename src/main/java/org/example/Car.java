package org.example;

/**
 * Класс описвающий автомобили
 */
public class Car {

    /**
     * Поле регистрационного номера
     */
    private final String  regNumber;

    /**
     * Конструктор класса {@link Car}
     * @param regNumber - Регистрационный номер
     */
    public Car(String regNumber){
        this.regNumber = regNumber;

    }

    /**
     * Геттер поля {@link Car#regNumber}
     * @return - рег.номер
     */
    public String getRegNumber(){
        return regNumber;
    }

    /**
     * Метод отображения в в иде строки.
     * P.S. В задании не требовался. Выполнен для удобства и наглядности
     * @return - строковое представление {@link Car}
     */
    @Override
    public String toString(){

        return "Car{regNumber = "+regNumber +'}';
    }




    }





