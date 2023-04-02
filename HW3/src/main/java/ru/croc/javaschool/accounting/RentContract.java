package ru.croc.javaschool.accounting;

import ru.croc.javaschool.transport.Vehicle;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Класс, описывающий поведение договоров арендного центра
 */
public class RentContract {
    /**
     * Арендованный транспорт
     */
    private final Vehicle[] rentedVehicles;
    /**
     * Арендный центр
     */
    private final RentCenter rentCenter;
    /**
     * дата начала аренды
     */
    private final LocalDateTime dateRentStart;
    /**
     * дата конца аренды
     */
    private final LocalDateTime dateRentFinish;


    /**
     * Конструктор
     * @param rentCenter - арендный центр
     * @param rentedVehicles - арендованный транспорт
     * @param dateRentStart - дата начала аренды
     * @param dateRentFinish = дата конца аренды
     */
    public RentContract(RentCenter rentCenter, Vehicle[] rentedVehicles, LocalDateTime dateRentStart, LocalDateTime dateRentFinish) {
        this.rentCenter = rentCenter;
        this.rentedVehicles = rentedVehicles;
        this.dateRentStart = dateRentStart;
        this.dateRentFinish = dateRentFinish;
    }


    /**
     * Получение арендованного транспорта
     * @return  арендованный транспорт
     */
    public Vehicle[] getRentedVehicles() {
        return rentedVehicles;

    }

    /**
     * Получение даты начала аренды
     * @return дата начала аренды
     */
    public LocalDateTime getDateRentStart() {
        return dateRentStart;
    }

    /**
     * Получение даты конца аренды
     * @return
     */
    public LocalDateTime getDateRentFinish() {
        return dateRentFinish;
    }

    /**
     * Получение арендного центра
     * @return арендный центр
     */
    public RentCenter getRentCenter() {
        return rentCenter;
    }
}
