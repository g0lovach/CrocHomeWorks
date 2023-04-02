package ru.croc.javaschool.accounting;

import ru.croc.javaschool.transport.Vehicle;

import java.time.LocalDateTime;

/**
 * Класс, описывающий движения активов центра аренды (приход/расход транспорта)
 */
public class Movement {
    /**
     * Приход/расход
     */
    private final boolean coming;
    /**
     * Транспорт текущего движения
     */
    private final Vehicle movedVehicle;
    /**
     * Дата движения
     */
    private final LocalDateTime dtOfMovement;

    /** геттер типа движения
     * @return  - тип движения
     */
    public boolean isComing() {
        return coming;
    }

    /**
     * Получение транспорта движения
     * @return транспорт движения
     */
    public Vehicle getMovedVehicle() {
        return movedVehicle;
    }

    /**
     * получение даты движения
     * @return  дата движения
     */
    public LocalDateTime getDtOfMovement() {
        return dtOfMovement;
    }


    public Movement(boolean coming, Vehicle movedVehicle) {
        this.coming = coming;
        this.movedVehicle = movedVehicle;
        this.dtOfMovement = LocalDateTime.now();
    }

}
