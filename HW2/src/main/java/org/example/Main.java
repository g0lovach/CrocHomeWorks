package org.example;
import org.example.obj.Car;
import org.example.obj.DeparturePoint;
import org.example.obj.EntryPoint;
import org.example.obj.Parking;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //Создаем автомобили
        Car c1 = new Car("001");
        Car c2 = new Car("002");
        Car c3 = new Car("003");
        //Создаем въезды/выезды
        EntryPoint ep1 = new EntryPoint(1,"parking 1 ep 1" );
        EntryPoint ep2 = new EntryPoint(2,"parking 1 ep 2" );
        DeparturePoint dp1 = new DeparturePoint(1,"parking 1 dp 1" );
        //Создаем парковку
        Parking p1 = new Parking(new EntryPoint[]{ep1,ep2},new DeparturePoint[]{dp1}, 2  );
        //Попытка въезда 3-х авто (мест на парковке 2)
        p1.doEntry(ep1, c1.getRegNumber());
        p1.doEntry(ep2, c2.getRegNumber());
        p1.doEntry(ep2,c3.getRegNumber());
        // Выводим кол-во свободных мест, возможность въезда, неудачные попытки въезда
        System.out.println(p1.getParkingFreeSidesCount());
        System.out.println(p1.isAvailableToEntry());
        System.out.println(Arrays.toString(p1.getFailedEntryTries()));
        //Совершаем выезд одной машины (и выведем информацию о выезде)
        p1.doDeparture(dp1, c1.getRegNumber());
        System.out.println(dp1);
        //Получаем кол-во свободных мест после выезда и проверяем доступность въезда
        System.out.println(p1.getParkingFreeSidesCount());
        System.out.println(p1.isAvailableToEntry());
        // Въезжаем по-новой
        p1.doEntry(ep1,c3.getRegNumber());
        //выводим неудачные попытки въезда,список авто через въезды ( и проверка ну дупликаты),
        // список авто через выезд (и проверка ну дупликаты)
        System.out.println(Arrays.toString(p1.getFailedEntryTries()));
        System.out.println(Arrays.toString(p1.getCarsByEntryPoint(ep1)));
        System.out.println(Arrays.toString(p1.getCarsByEntryPoint(ep2)));
        System.out.println(Arrays.toString(p1.getCarsByDeparturePoint(dp1)));
        p1.doDeparture(dp1, "001");
        p1.doEntry(ep1, "001");
        System.out.println(Arrays.toString(p1.getCarsByEntryPoint(ep1)));
        // Выводим информацию о парковке
        System.out.println(p1);








    }
}