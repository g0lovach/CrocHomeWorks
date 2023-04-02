package ru.croc.javaschool;

import ru.croc.javaschool.accounting.RentCenter;
import ru.croc.javaschool.accounting.RentContract;
import ru.croc.javaschool.accounting.TechnicalInspection;
import ru.croc.javaschool.transport.Vehicle;
import ru.croc.javaschool.transport.autos.Auto;
import ru.croc.javaschool.transport.autos.Car;
import ru.croc.javaschool.transport.autos.Truck;
import ru.croc.javaschool.transport.flyings.BusinessJet;
import ru.croc.javaschool.transport.flyings.Helicopter;
import ru.croc.javaschool.transport.individuals.ElectricScooter;
import ru.croc.javaschool.transport.individuals.Unicycle;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Демонстрационный класс
 */
public class Demo {
    public static void main(String[] args) {

        //Autos
        Car car1 = new Car(4, "sedan", 200, "full", 1000, "Lada 2109", 4);
        Car car2 = new Car(4, "sedan", 100, "full", 500, "Lada 2107", 3);
        Truck truck1 = new Truck(1000, 300, "back", 4000, "МАЗ - 1", 4);
        Truck truck2 = new Truck(2000, 600, "back", 5000, "МАЗ - 2", 2);

        //Flyings
        BusinessJet businessJet1 = new BusinessJet(4,20000 , 50000, "bj1", 100);
        BusinessJet businessJet2 = new BusinessJet(6,25000 , 100000, "bj2", 300);
        Helicopter helicopter1 = new Helicopter(6, 1000,10000, "h1", 10);
        Helicopter helicopter2 = new Helicopter(4, 2000,8000, "h2", 20);

        //Individuals
        Unicycle unicycle = new Unicycle(true, 10,100,"u1",1);
        ElectricScooter electricScooter = new ElectricScooter(true, 10,100,"u1",1);

        //Initialize Rent Center and crate movements of coming vehicles
        RentCenter rentCenter1 = new RentCenter();
        rentCenter1.appendVehicle(car1);
        rentCenter1.appendVehicle(car2);
        rentCenter1.appendVehicle(truck1);
        rentCenter1.appendVehicle(truck2);
        rentCenter1.appendVehicle(businessJet1);
        rentCenter1.appendVehicle(businessJet2);
        rentCenter1.appendVehicle(helicopter1);
        rentCenter1.appendVehicle(helicopter2);
        rentCenter1.appendVehicle(unicycle);
        rentCenter1.appendVehicle(electricScooter);
        rentCenter1.appendVehicle(car1);// выскочит ошибка. car 1 уже учтен в системе



        //Rent Contracts
        LocalDateTime dt1 = LocalDateTime.of(2024,1,1,0,0);
        LocalDateTime dt2 = LocalDateTime.of(2024,1,10,0,0);

        LocalDateTime dt3 = LocalDateTime.of(2024,2,1,0,0);
        LocalDateTime dt4 = LocalDateTime.of(2024,2,7,0,0);

        LocalDateTime dt5 = LocalDateTime.of(2024,1,7,0,0);
        LocalDateTime dt6 = LocalDateTime.of(2024,1,10,0,0);

        RentContract rentContract1False = new RentContract(rentCenter1, new Vehicle[]{car1,truck1, truck2, businessJet1}, dt1,dt2 );
        RentContract rentContract1True = new RentContract(rentCenter1, new Vehicle[]{car1,truck1, truck2}, dt1,dt2 );
        RentContract rentContract2 = new RentContract(rentCenter1, new Vehicle[]{car1,helicopter1, businessJet2}, dt5, dt6);
        RentContract rentContract3 = new RentContract(rentCenter1, new Vehicle[]{truck2,unicycle, electricScooter}, dt3,dt4 );

        TechnicalInspection technicalInspection = new TechnicalInspection(rentContract1False, businessJet1,true);

        // Accounting

        rentCenter1.appendRentContract(rentContract1False); // не сохранится, т.к. неисправный транспорт не подлежит аренде
        rentCenter1.appendRentContract(rentContract1True);
        rentCenter1.appendRentContract(rentContract2); // не должен сохраниться. выскочит предупреждение
        rentCenter1.appendRentContract(rentContract3);

        rentCenter1.deleteDestroyedVehicle(businessJet1); // удалится с баланса и создастся расход соответствующей машины (movement)





        System.out.println(rentCenter1.getRentContracts().length);
        System.out.println(rentCenter1.getVehicles().length);

        //Получаем арендованные vehicles (rentContract1True и rentContract3, т.к они прошли). Дупликаты не учитываются
        System.out.println(Arrays.toString(rentCenter1.getRentedVehicles(
                LocalDateTime.of(2024, 1, 1, 0, 0),
                LocalDateTime.of(2024, 3, 1, 0, 0))));

        //Получаем свободные vehicles (rentContract1False и rentContract2, т.к они не прошли). Дупликаты не учитываются
        System.out.println(Arrays.toString(rentCenter1.getFreeVehicles(LocalDateTime.of(2024, 1, 1, 0, 0)
                , LocalDateTime.of(2024, 3, 1, 0, 0))));
        ;


        //Получаем свободные vehicles по категориям.
        rentCenter1.getFreeVehiclesForCategory( LocalDateTime.of(2024, 1, 1, 0, 0)
                ,LocalDateTime.of(2024, 3, 1, 0, 0));


        // Получаем отчет со статистикой
        rentCenter1.getReport(LocalDateTime.of(2024, 1, 1, 0, 0)
                ,LocalDateTime.of(2024, 3, 1, 0, 0));












    }


}