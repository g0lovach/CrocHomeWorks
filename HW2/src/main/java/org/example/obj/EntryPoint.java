package org.example.obj;

import java.util.Arrays;

/**
 * Класс описания въездов
 */
public class EntryPoint {

    /**
     * Номер въезда
     */
   private final int id;
    /**
     * Описание
     */
   private final String description;
    /**
     * Машины, соершавшие попытку въезда
     */
   private Car[] guests;


    /**
     * Геттер поля {@link EntryPoint#guests}
     * @return - Массив машин, соершавших попытку въезда
     */
    public Car[] getGuests() {
        return guests;
    }

    /**
     * Полный конструктор класса {@link EntryPoint}. Следует использовать для описание объектов не "с нуля".
     * (аналогично {@link DeparturePoint#DeparturePoint(int, String, Car[])})
     * @param id - номер въезда
     * @param description - описание
     * @param guests - Массив машин, соершавших попытку въезда
     */
    public EntryPoint(int id, String description, Car[] guests) {
        this.id = id;
        this.description = description;
        this.guests = guests;
    }

    /**
     * Неполный конструктор класса {@link EntryPoint}.
     * Используется аналогично {@link DeparturePoint#DeparturePoint(int, String)}  DeparturePoint}
     * @param id - номер въезда
     * @param description - описание
     */
    public EntryPoint(int id, String description) {
        this(id,description, new Car[]{});
    }


    /**
     * Метод добавления авто в массив посещавших въезд
     * @param car - авто для добавления в {@link EntryPoint#guests}
     */
    public void appendGuest(Car car){
        guests = Arrays.copyOf(guests, guests.length + 1);
        guests[guests.length-1] = car;

    }

    /**
     * Вспомогатеьный метод для метода{@link Parking#getCarsByEntryPoint(EntryPoint)}.
     * Возвращает авто из {@link EntryPoint#guests} по рег.номеру.
     * @param regNumber - рег.номер авто
     * @return авто
     */
    public Car getCarByRegNumber(String regNumber){
        for (Car car : guests){
            if (regNumber.equals(car.getRegNumber())){
                return car;
            }

        }
        return new Car("000");
    }

    /**
     * Метод преобразования в String.
     * P.S. В задаче требований на добавление этого метода не было, но я его прописал для получения наглядной информации
     * об объекте
     * @return - строковое описание {@link EntryPoint}
     */
    @Override
    public String toString(){
        return "EntryPoint{" + "id = " + id + "; " + "description = "
                + description + "; guests = " + Arrays.toString(guests)+"]}";



    }

}
