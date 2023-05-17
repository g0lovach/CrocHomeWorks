package ru.croc.javaschool.deserialize;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.sql.Timestamp;

/**
 * Прилет
 */
public class Arrival {
    /**
     * Откуда
     */
    @JacksonXmlProperty(localName = "cityFrom")
    private String cityFrom;

    /**
     * Когда
     */
    @JacksonXmlProperty(localName = "date")
    private String date;

    /**
     * Рег. номер самолета
     */
    @JacksonXmlProperty(localName = "boardRegNumber")
    private String boardRegNumber;


    /**
     * Конструктор
     * @param cityFrom
     * @param date
     * @param boardRegNumber
     */
    public Arrival(String cityFrom,String date, String boardRegNumber) {
        this.cityFrom = cityFrom;
        this.date = date;
        this.boardRegNumber = boardRegNumber;
    }

    /**
     * Конструктор
     */
    public Arrival() {
    }

    /**
     * @return город откуда
     */
    public String getCityFrom() {
        return cityFrom;
    }

    /**
     * @return когда прибыл
     */
    public String getDate() {
        return date;
    }

    /**
     * @return рег.номер самолета
     */
    public String getBoardRegNumber() {
        return boardRegNumber;
    }

    @Override
    public String toString() {
        return "Arrival{" +
                "cityFrom='" + cityFrom + '\'' +
                ", date='" + date + '\'' +
                ", boardRegNumber='" + boardRegNumber + '\'' +
                '}';
    }
}
