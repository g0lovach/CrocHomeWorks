package ru.croc.javaschool.transport.individuals;

import ru.croc.javaschool.accounting.RentCenter;
import ru.croc.javaschool.accounting.TechnicalInspection;

/**
 * Класс, описывающий электро-самокаты
 */
public class ElectricScooter extends Individual{
    /**
     * НАличие клаксона
     */
    boolean ringable;

    /**
     * Конструктор
     */
    public ElectricScooter() {

        super();
    }

    /**
     * @return наличие клаксона
     */
    public boolean isRingable() {
        return ringable;
    }


    /**
     * Конструктор
     * @param ringable - наличие клаксона
     * @param powerReserve - запас хода
     * @param cost - цена
     * @param model - модель
     * @param placesCount - кол-во пассажирнских мест
     */
    public ElectricScooter(boolean ringable, int powerReserve, int cost, String model, int placesCount){
        super(powerReserve, cost, model, placesCount);
        this.ringable = ringable;

    }

    /**
     * @return запас хода
     */
    @Override
    public int getPowerReserve() {
        return super.getPowerReserve();
    }

    /**
     * @param rentCenter сеттер айди
     */
    public void setId(RentCenter rentCenter){

        super.setId(rentCenter);
    }

    /**
     * @return айди в системе
     */
    @Override
    public int getId() {

        return super.getId();
    }


    /**
     * Установка поврежденности отталкиаваясь от тех.осмотра
     * @param technicalInspection - тех. осмотр
     */
    @Override
    public void setDestroyed(TechnicalInspection technicalInspection) {
        super.setDestroyed(technicalInspection);
    }

    /**
     * @return поврежденность
     */
    @Override
    public boolean isDestroyed() {
        return super.isDestroyed();
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
     * @return кол-во мест
     */
    @Override
    public int getPlacesCount() {
        return super.getPlacesCount();
    }

    /**
     * @return строковое представление объекта
     */
    @Override
    public String toString() {
        return "ElectricScooter{id=" + Integer.toString(this.getId())+", destroyed="+Boolean.toString(this.isDestroyed())
                +", ringable = "+ this.ringable + super.getInfo() + "}\n";
    }

}
