package ru.croc.javaschool.accounting;

import ru.croc.javaschool.transport.Vehicle;

/**
 * Класс описывающий проведение тех.осмотра транспорта
 */
public class TechnicalInspection {

    /**
     * Вердикт
     */
   private final boolean verdict;

    /**
     * Договор
     */
    private final RentContract rentContract;

    /**
     * Проверяемый транспорт
     */
   private final Vehicle vehicle;

    /**
     * Конструктор
     * @param rentContract  - договор
     * @param vehicle - транспорт
     * @param verdict - вердикт
     */
    public TechnicalInspection(RentContract rentContract, Vehicle vehicle, boolean verdict){
        this.rentContract = rentContract;
        this.vehicle = vehicle;
        this.verdict = verdict;
        vehicle.setDestroyed(this);

    }

    /**
     * Получение вердикта
     * @return  вердикт
     */
    public boolean isVerdict() {
        return verdict;
    }

    /**
     * Получение договора
     * @return договор
     */
    public RentContract getRentContract() {
        return rentContract;
    }

    /**
     * Получение транспорта
     * @return транспорт
     */
    public Vehicle getVehicle() {
        return vehicle;
    }
}
