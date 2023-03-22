package org.example;

import java.util.Arrays;


/**
 * Класс, описывающий выезды с парковки.
 *
 * @author gol0vach
 */
public class DeparturePoint {


    /**
     * Поле номера выезда
     */
   private final int id;
    /**
     * Поле описания выезда
     */
   private final String description;
    /**
     * Массив авто, проезжавших выезд
     */
   private Car[] guests;


    /**
     * Полный конструктор класса {@link DeparturePoint}.
     * Стоит применять, если создается объект, для которого уже имеются какие-то исходные данные (например, если нам
     * известно, что через этот выезд уже кто-то проезжал и мы хотим это зафиксировать.
     * Для обозначения этого факта и передаем массив машин)
     * @param id - номер выезда
     * @param description - текстовое описание выезда
     * @param guests - авто, которые проезжали через выезд
     */
    public DeparturePoint(int id, String description, Car[] guests) {
        this.id = id;
        this.description = description;
        this.guests = guests;
    }

    /**
     * Неполный конструктор класса {@link DeparturePoint}. Стоит применять,
     *  если объект создается "с нуля" (т.е. выезд, через который еще никто не проезжал).
     *
     * @param id - номер выезда
     * @param description - текстовое описание выезда
     */
    public DeparturePoint(int id, String description) {
        this(id, description, new Car[]{});
    }




    /**
     * Геттер поля guests.
     * @return - guests - массив авто, проезжавших этот выезд
     */
    public Car[] getGuests() {
        return guests;
    }


    /**
     * Метод, фиксирующий факт пересечения некоторым авто выезда.
     * @param car - авто, проехавшее выезд
     */
    public void appendGuest(Car car){
        guests = Arrays.copyOf(guests, guests.length+1);
        guests[guests.length-1] = car;

    }

    /**
     * Метод преобразования в String.
     * P.S. В задаче требований на добавление этого метода не было, но я его прописал для получения наглядной информации
     * об объекте
     * @return - строковое описание DeparturePoint
     */
    @Override
    public String toString(){
        String result = "DeparturePoint{" + "id = " + id + "; " + "description = "
                + description + "; guests = " + Arrays.toString(guests);


        return result +"]\n}";
    }
}
