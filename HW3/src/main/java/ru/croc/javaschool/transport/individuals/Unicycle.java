package ru.croc.javaschool.transport.individuals;

import ru.croc.javaschool.accounting.RentCenter;
import ru.croc.javaschool.accounting.TechnicalInspection;

/**
 * Класс, описывыающий моно-колесо
 */
public class Unicycle extends Individual{
    /**
     * Есть ли сиденье
     */
    boolean seatable;

    /**
     * Конструктор
     */
    public Unicycle() {
        super();
    }

    /**
     * @return возможность сесть
     */
    public boolean isSeatable() {
        return seatable;
    }


    /**
     * Конструктор
     * @param seatable - есть ли сиденье
     * @param powerReserve - запас хода
     * @param cost - цена
     * @param model - модель
     * @param placesCount - кол-во мест
     */
    public Unicycle(boolean seatable, int powerReserve, int cost, String model, int placesCount){
        super(powerReserve, cost, model, placesCount);
        this.seatable = seatable;

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
     * установка поврежденности на основе тех.осмотра
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
     * @return кол-во пассажирских мест
     */
    @Override
    public int getPlacesCount() {
        return super.getPlacesCount();
    }

    /**
     * @return Строковое представление объекта
     */
    @Override
    public String toString() {
        return "Unicycle{id=" + Integer.toString(this.getId())+", destroyed="+Boolean.toString(this.isDestroyed())
                +", seatable = "+ this.seatable + super.getInfo() + "}\n";
    }
}
