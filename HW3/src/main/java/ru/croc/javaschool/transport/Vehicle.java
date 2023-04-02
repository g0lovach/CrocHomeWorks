package ru.croc.javaschool.transport;

import ru.croc.javaschool.accounting.RentCenter;
import ru.croc.javaschool.accounting.TechnicalInspection;

/**
 * Класс, описывающий транеспорт
 */
public class Vehicle {
    /**
     * айди в системе
     */
    private int id;
    /**
     * поврежденность
     */
    private boolean destroyed;

    /**
     * Стоимость
     */
    private int cost;

    /**
     * Модель
     */
    private String model;

    /**
     * Количество пассажирскийх мест
     */
    private int placesCount;

    /**
     * @return геттер числа пассажирских мест
     */
    public int getPlacesCount(){
        return this.placesCount;
    }

    /**
     * Конструктор
     */
    protected Vehicle(){
        this.destroyed = false;
        this.id = -1;
    }

    /**
     * Конструктор
     * @param cost - цена
     * @param model - модель
     * @param placesCount - кол-во пассажирских мест
     */
    protected Vehicle(int cost, String model, int placesCount){
        this();
        this.cost = cost;
        this.model = model;
        this.placesCount = placesCount;
    }

    /**
     * @param rentCenter сеттер айди
     */
    public void setId(RentCenter rentCenter){
        this.id = rentCenter.getCurrentVehicleId();
    }

    /**
     * @return поврежденность
     */
    public boolean isDestroyed() {
        return destroyed;
    }

    /**
     * @return цена
     */
    public int getCost() {
        return cost;
    }


    /**
     * @return модель
     */
    public String getModel() {
        return model;
    }



    /**
     *
     * @return айди
     */
    public int getId() {
        return id;
    }

    /**
     * сеттер поврежденности
     * @param technicalInspection - тех. осмотр
     */
    public void setDestroyed(TechnicalInspection technicalInspection) {
        this.destroyed = technicalInspection.isVerdict();
    }


    /**
     * @return строковое представление некоторой информации о транспорте
     */
    public String getInfo() {
        return
                ", cost = " + cost +
                ", model = " + model;
    }
}
