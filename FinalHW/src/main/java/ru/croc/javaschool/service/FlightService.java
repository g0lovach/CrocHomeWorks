package ru.croc.javaschool.service;

import ru.croc.javaschool.models.Flight;
import ru.croc.javaschool.repository.FlightRepository;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Сервис рейсов
 */
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    /**
     * Создание новой записи в БД о рейсе
     * @param date
     * @param otherCity
     * @param boardRegNumber
     * @return
     * @throws SQLException
     */
    public boolean createNewFlight( Timestamp date, String otherCity, String boardRegNumber) throws SQLException {
        int id = flightRepository.findActualId();
        var creatingFlight = new Flight();
        creatingFlight.setId(id);
        creatingFlight.setDate(date);
        creatingFlight.setOtherCity(otherCity);
        creatingFlight.setBoardRegNumber(boardRegNumber);
        return flightRepository.create(creatingFlight);
    }

    /**
     *
     * @return выборка всех записей из БД
     */
    public List<Flight> findAll() {
        return  flightRepository.findAll();
    }

    /**
     * @return актуальный ID
     * @throws SQLException
     */
    public int findId() throws SQLException {
        return flightRepository.findActualId();
    }


}
