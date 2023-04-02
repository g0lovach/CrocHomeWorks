package ru.croc.javaschool.transport.autos;

import ru.croc.javaschool.accounting.RentCenter;
import ru.croc.javaschool.transport.Vehicle;
import ru.croc.javaschool.accounting.TechnicalInspection;

/**
 * Класс описывающий категорию автомобилей
 */
public class Auto extends Vehicle {

    /**
     * тип Привода
     */
    private String drive;

    /**
     * Мощность двигателя
     */
    private int powerOfEngine;

    /**
     * конструктор
     */
    protected Auto(){
        super();
    }

    /**
     * конструктор
     * @param drive
     * @param powerOfEngine
     * @param cost
     * @param model
     * @param placesCount
     */
    protected Auto(String drive, int powerOfEngine, int cost, String model, int placesCount) {
         super(cost,model,placesCount);
        this.drive = drive;
        this.powerOfEngine = powerOfEngine;
    }

    /**
     * геттер привода
     * @return
     */
    public String getDrive() {
        return drive;
    }


    /**
     * @return мощность двигателя
     */
    public int getPowerOfEngine() {
        return powerOfEngine;
    }


    /**
     * @return цена
     */
    @Override
    public int getCost(){
         return super.getCost();
    }

    /**
     * @return модель
     */
    @Override
    public String getModel(){
        return super.getModel();
    }


    /**
     * @return айди
     */
    @Override
    public int getId() {
        return super.getId();
    }

    /**
     * @return поврежденность
     */
    @Override
    public boolean isDestroyed() {
        return super.isDestroyed();
    }

    /**
     * Установка поврежденности транспорта
     * @param technicalInspection - тех осмотр на основании которого будет уставновлена поврежденность
     */
    @Override
    public void setDestroyed(TechnicalInspection technicalInspection) {
        super.setDestroyed(technicalInspection);
    }

    /**
     * @param rentCenter сеттер айди
     */
    @Override
    public void setId(RentCenter rentCenter){
        super.setId(rentCenter);
    }


    /**
     * @return получение кол-ва пассажирских мест
     */
    @Override
    public int getPlacesCount() {
        return super.getPlacesCount();
    }

    /**
     * @return Получение необходимой потомку строковой информации об объекте
     */
    public String getInfo(){
        return " drive = " + drive + " powerOfEngine = " + powerOfEngine + super.getInfo();
    }

    /**
     * @return Строковое представление объекта
     */
    @Override
    public String toString() {
        return "Auto{" +
                "drive='" + drive +
                ", powerOfEngine=" + powerOfEngine +
                super.getInfo()+
                "}\n";
    }
}
