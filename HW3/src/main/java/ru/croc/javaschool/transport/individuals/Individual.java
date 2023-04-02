package ru.croc.javaschool.transport.individuals;

import ru.croc.javaschool.accounting.RentCenter;
import ru.croc.javaschool.accounting.TechnicalInspection;
import ru.croc.javaschool.transport.Vehicle;

/**
 * Класс, описывающий категорию индивидуальных средств передвижения
 */
public class Individual extends Vehicle {

    /**
     * Запас хода
     */
    private int powerReserve;

    /**
     * Конструктор
     */
    protected Individual() {
        super();
    }

    /**
     * @return запас хода
     */
    public int getPowerReserve() {
        return powerReserve;
    }

    /**
     * Конструктор
     * @param powerReserve - запас хода
     * @param cost - цена
     * @param model - модель
     * @param placesCount - кол-во мест
     */
    protected Individual(int powerReserve, int cost, String model, int placesCount) {
        super( cost, model, placesCount);
        this.powerReserve = powerReserve;
    }

    /**
     * @return некоторая информация об объекте в строковом виде
     */
    public String getInfo(){
        return ", powerReserve = " + powerReserve + super.getInfo();
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
     * @return айди в системе
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
     * установка поврежденности на основе результатов тех осмотра
     * @param technicalInspection - тех. осмотр
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
        return "Individual{" +
                "powerReserve=" + powerReserve + super.getInfo()+
                "}\n";
    }
}
