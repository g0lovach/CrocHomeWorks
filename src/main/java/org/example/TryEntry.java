package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Класс, описывающий попытки въезда на парковку
 */
public class TryEntry {
    /**
     * Поле регистрационного номера автомобиля, совершившего попытку въезда
     */
    private final String carsRegNumber;
    /**
     * Поле точки въезда, попытка пересечения которой была совершена
     */
    private final EntryPoint entryPoint;
    /**
     * Поле даты и времени предполагаемого въезда
     */
     private final String dt;

    /**
     * Поле результата въезда (удался или нет)
     */
    private final boolean result;

    /**
     * Полный конструктор класса {@link TryEntry}. Стоит использовать, когда объект
     * создатся не "с нуля"
     * (аналогично {@link DeparturePoint#DeparturePoint(int, String, Car[])}).
     * @param carsRegNumber - регитсрационный номер авто
     * @param entryPoint - точка въезда
     * @param dt - дата и время
     * @param result - результат
     */
    public TryEntry(String carsRegNumber, EntryPoint entryPoint, String dt, boolean result) {
        this.carsRegNumber = carsRegNumber;
        this.entryPoint = entryPoint;
        this.dt = dt;
        this.result = result;
    }

    /**
     * Неполный конструктор класса {@link TryEntry}. Стоит использовать для созданиря объектов "с нуля".
     * (аналогично {@link DeparturePoint#DeparturePoint(int, String)})
     * @param carsRegNumber - регитсрационный номер авто
     * @param entryPoint - точка въезда
     * @param result - результат
     */
    public TryEntry(String carsRegNumber, EntryPoint entryPoint, boolean result) {
        this(carsRegNumber, entryPoint, LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm")), result);
    }

    /**
     * Геттер поля {@link TryEntry#carsRegNumber}
     * @return - рег.номер
     */
    public String getCarsRegNumber() {
        return carsRegNumber;
    }
    /**
     * Геттер поля {@link TryEntry#entryPoint}
     * @return - въезд
     */
    public EntryPoint getEntryPoint() {
        return entryPoint;
    }
    /**
     * Геттер поля {@link TryEntry#result}
     * @return - результат попытки
     */
    public boolean isResult() {
        return result;
    }

    /**
     * Метод преобразования в String.
     * P.S. В задаче требований на добавление этого метода не было, но я его прописал для получения наглядной информации
     * об объекте
     * @return - строковое описание {@link EntryPoint}
     */
    @Override
    public String toString(){

        return "TryEntry{ " + "carsRegNumber = " + carsRegNumber + "; " + "entryPoint = "
                + entryPoint.toString() + "; " + "datetime =  " + dt + "; " + "result = " + this.result + '}';
    }




}
