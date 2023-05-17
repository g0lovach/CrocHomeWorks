package ru.croc.javaschool.deserialize;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Вылеты
 */
public class Departure {

    /**
     * город куда вылетел
     */
    @JacksonXmlProperty(localName = "cityTo")
    private String cityTo;

    /**
     * когда вылетел
     */
    @JacksonXmlProperty(localName = "date")
    private String date;

    /**
     * рег. номер самолета
     */
    @JacksonXmlProperty(localName = "boardRegNumber")
    private String boardRegNumber;

    /**
     * Конструктор
     * @param cityTo
     * @param date
     * @param boardRegNumber
     */
    public Departure(String cityTo, String date, String boardRegNumber) {
        this.cityTo = cityTo;
        this.date = date;
        this.boardRegNumber = boardRegNumber;
    }

    /**
     * Конструктор
     */
    public Departure() {
    }

    /**
     * @return город куда
     */
    public String getCityTo() {
        return cityTo;
    }

    /**
     * @return дата
     */
    public String getDate() {
        return date;
    }

    /**
     * @return рег. номер борта
     */
    public String getBoardRegNumber() {
        return boardRegNumber;
    }

    @Override
    public String toString() {
        return "Departure{" +
                "cityTo='" + cityTo + '\'' +
                ", date='" + date + '\'' +
                ", boardRegNumber='" + boardRegNumber + '\'' +
                '}';
    }
}
