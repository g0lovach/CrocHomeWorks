package ru.croc.javaschool.models;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * модель таблицы факта прилета/вылета
 */
public class Flight {
    public static final String TABLE_NAME = "Flight";

    /**
     * id
     */
    private int id;

    /**
     * дата
     */
    private Timestamp date;

    /**
     * город куда/откуда
     */
    private String otherCity;

    /**
     * рег. номер судна
     */
    private String boardRegNumber;


    /**
     * конструктор
     */
    public Flight() {
    }

    /**
     * конструктор
     * @param id
     * @param date
     * @param otherCity
     * @param boardRegNumber
     */
    public Flight(int id, Timestamp date, String otherCity, String boardRegNumber) {
        this.id = id;
        this.date = date;
        this.otherCity = otherCity;
        this.boardRegNumber = boardRegNumber;
    }

    /**
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * установка id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return дата
     */
    public Timestamp getDate() {
        return date;
    }

    /**
     * Установка даты
     * @param date
     */
    public void setDate(Timestamp date) {
        this.date = date;
    }

    /**
     * @return город куда/откуда
     */
    public String getOtherCity() {
        return otherCity;
    }

    /**
     * установка города куда/откуда
     * @param otherCity
     */
    public void setOtherCity(String otherCity) {
        this.otherCity = otherCity;
    }

    /**
     * @return рег. номер судна
     */
    public String getBoardRegNumber() {
        return boardRegNumber;
    }

    /**
     * установка рег. номера судна
     * @param boardRegNumber
     */
    public void setBoardRegNumber(String boardRegNumber) {
        this.boardRegNumber = boardRegNumber;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", date=" + date +
                ", otherCity='" + otherCity + '\'' +
                ", boardRegNumber='" + boardRegNumber + '\'' +
                '}';
    }

}
