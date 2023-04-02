package ru.croc.javaschool.transport.flyings;

import ru.croc.javaschool.accounting.RentCenter;
import ru.croc.javaschool.accounting.TechnicalInspection;

/**
 * Класс, описывающий вертолеты
 */
public class Helicopter extends Flying {
    /**
     * кол-во лопастей
     */
    int bladesCount;

    /**
     * конструктор
     */
    public Helicopter() {

        super();
    }

    /**
     * @return кол-во лопастей
     */
    public int getBladesCount() {
        return bladesCount;
    }


    /**
     * Конструктор
     * @param bladesCount - кол-во лопастей
     * @param maxHeight - максимальная высота полета
     * @param cost - цена
     * @param model - модель
     * @param placesCount - кол-во мест
     */
    public Helicopter(int bladesCount, int maxHeight , int cost, String model, int placesCount) {
        super(maxHeight,  cost,  model,  placesCount);
        this.bladesCount = bladesCount;
    }

    /**
     * @return максимальная высота полета
     */
    @Override
    public int getMaxHeight() {
        return super.getMaxHeight();
    }


    /**
     * @return цена
     */
    @Override
    public int getCost() {
        return super.getCost();
    }

    /**
     * @return модель
     */
    @Override
    public String getModel() {
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
     * установка поврежденности
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
    public void setId(RentCenter rentCenter) {
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
     * @return строковое представление объекта
     */
    @Override
    public String toString() {
        return "Helicopter{id=" + Integer.toString(this.getId())+", destroyed="+Boolean.toString(this.isDestroyed())
                +", bladesCount = "+ this.bladesCount + super.getInfo() + "}\n";
    }
}
