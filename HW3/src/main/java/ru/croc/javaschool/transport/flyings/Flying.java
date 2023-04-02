package ru.croc.javaschool.transport.flyings;

import ru.croc.javaschool.accounting.RentCenter;
import ru.croc.javaschool.accounting.TechnicalInspection;
import ru.croc.javaschool.transport.Vehicle;


/**
 * Класс, описывающий летательный транеспорт
 */
public class Flying extends Vehicle {

    /**
     * максимальная высота полета
     */
    private int maxHeight;

    /**
     * @return максимальная высота полета
     */
    public int getMaxHeight() {
        return maxHeight;
    }

    /**
     * Конструктор
     */
    protected Flying() {
        super();
    }


    /**
     * Конструктор
     * @param maxHeight - максимальная высота полета
     * @param cost - цена
     * @param model - модель
     * @param placesCount - кол-во мест
     */
    protected Flying(int maxHeight, int cost, String model, int placesCount) {
        super( cost, model, placesCount);
        this.maxHeight = maxHeight;
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
     * установка поврежденности на основе тех. осмоотра
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
     * @return кол-во мест
     */
    @Override
    public int getPlacesCount() {
        return super.getPlacesCount();
    }

    /**
     * @return информация об объекте в строковом виде
     */
    public String getInfo(){
        return ", maxHeight = " + maxHeight + super.getInfo();
    }

    /**
     * @return строковое представление объекта
     */
    @Override
    public String toString() {
        return "Flying{" +
                "maxHeight=" + maxHeight + super.getInfo()+
                "}\n";
    }
}
