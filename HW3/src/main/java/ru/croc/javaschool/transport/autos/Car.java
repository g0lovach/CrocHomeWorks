package ru.croc.javaschool.transport.autos;

import ru.croc.javaschool.accounting.RentCenter;
import ru.croc.javaschool.accounting.TechnicalInspection;

/**
 * Класс, описывающий легковой автомобиль
 */
public class Car extends Auto{

    /**
     * кол-во дверей
     */
    private int doorsCount;

    /**
     * Тип кузова
     */
    private String type;

    /**
     * @return геттер типа кузова
     */
    public String getType() {
        return type;
    }


    /**
     * @return геттер кол-ва дверей
     */
    public int getDoorsCount() {
        return doorsCount;
    }


    /**
     * конструктор класса
     */
    public Car() {

        super();
    }

    /** конструктор класса
     * @param doorsCount - кол-во дверей
     * @param type - тип кузова
     * @param powerOfEngine - мощность двигателя
     * @param drive - привод
     * @param cost - цена
     * @param model - модель
     * @param placesCount - кол-во мест
     */
    public Car(int doorsCount, String type, int powerOfEngine, String drive, int cost, String model, int placesCount){
        super(drive, powerOfEngine, cost, model, placesCount);
        this.doorsCount = doorsCount;
        this.type = type;

    }

    /**
     * @param rentCenter сеттер айди
     */
    public void setId(RentCenter rentCenter){

        super.setId(rentCenter);
    }


    /**
     * @return геттер id
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
     * {inheritDoc}
     * @return
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
     * @return - поврежденность
     */
    @Override
    public boolean isDestroyed() {
        return super.isDestroyed();
    }

    /**
     * @return Строковое представление
     */
    @Override
    public String toString() {
        return "Car{id=" + Integer.toString(this.getId())+", destroyed="+Boolean.toString(this.isDestroyed())
                +", type = "+ this.type + ", doorsCount = " + this.doorsCount + super.getInfo() + "}\n";
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

}
