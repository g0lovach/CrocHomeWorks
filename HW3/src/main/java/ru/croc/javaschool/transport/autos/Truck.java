package ru.croc.javaschool.transport.autos;

import ru.croc.javaschool.accounting.RentCenter;
import ru.croc.javaschool.accounting.TechnicalInspection;

/**
 * Класс, описывающий грузовики
 */
public class Truck extends Auto{
    /**
     * Грузоподъемность
     */
    private int tonnage;

    /**
     * Конструктор
     */
    public Truck(){

        super();
    }

    /**
     * @return тоннаж
     */
    public int getTonnage() {

        return tonnage;
    }


    /**
     * Конструктор
     * @param tonnage - тоннаж
     * @param powerOfEngine - мощность двигателя
     * @param drive - привод
     * @param cost - цена
     * @param model - модель
     * @param placesCount - кол-во мест
     */
    public Truck(int tonnage,  int powerOfEngine, String drive, int cost, String model, int placesCount){
        super(drive, powerOfEngine, cost, model, placesCount);
        this.tonnage = tonnage;


    }

    /**
     * @param rentCenter сеттер айди
     */
    public void setId(RentCenter rentCenter){

        super.setId(rentCenter);
    }

    /**
     * @return айди
     */
    @Override
    public int getId() {

        return super.getId();
    }

    /**
     * @return мощность двигателя
     */
    @Override
    public  int getPowerOfEngine(){
        return super.getPowerOfEngine();
    }

    /**
     * @return привод
     */
    @Override
    public  String getDrive(){
        return super.getDrive();
    }

    /**
     * @param technicalInspection - тех осмотр на основании которого будет уставновлена поврежденность
     */
    @Override
    public void setDestroyed(TechnicalInspection technicalInspection) {
        super.setDestroyed(technicalInspection);
    }

    /**
     * @return Поврежденность
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



    @Override
    public String toString() {
        return "Truck{id=" + Integer.toString(this.getId())+", destroyed="+Boolean.toString(this.isDestroyed())
                +", tonnage = "+ this.tonnage + super.getInfo() + "}\n";
    }
}
