package ru.croc.javaschool.transport.flyings;


import ru.croc.javaschool.accounting.RentCenter;
import ru.croc.javaschool.accounting.TechnicalInspection;

/**
 * Класс описывающий бизнес-джеты
 */
public class BusinessJet extends Flying {

    /**
     * Количество двигателей
     */
    int enginesCount;

    /**
     * @return количество двигателей
     */
    public int getEnginesCount() {
        return enginesCount;
    }


    /**
     * Конструктор
     */
    public BusinessJet() {
        super();
    }

    /**
     * Конструктор
     * @param enginesCount - кол-во двигаетелей
     * @param maxHeight - максимальная высота полета
     * @param cost - цена
     * @param model - модель
     * @param placesCount - количество мест
     */
    public BusinessJet(int enginesCount,int maxHeight , int cost, String model, int placesCount) {
        super(maxHeight,  cost,  model,  placesCount);
        this.enginesCount = enginesCount;
    }

    /**
     * @return максимальная высота
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
     * @return кол-во мест
     */
    @Override
    public int getPlacesCount() {
        return super.getPlacesCount();
    }

    /**
     * @return строковое представление
     */
    @Override
    public String toString() {
        return "BusinessJet{id=" + Integer.toString(this.getId())+", destroyed="+Boolean.toString(this.isDestroyed())
                +", enginesCount = "+ this.enginesCount + super.getInfo() + "}\n";
    }
}
