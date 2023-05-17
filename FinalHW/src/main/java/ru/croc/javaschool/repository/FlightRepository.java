package ru.croc.javaschool.repository;

import ru.croc.javaschool.models.Flight;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * Интерфейс репозитория рейсов.
 */
public interface FlightRepository {

    /**
     * Метод инициализации таблицы.
     */
    void initTable();

    /**
     * Метод создания новой записи.
     */
    boolean create(Flight Flight);

    /**
     * Метод получения всех записей из таблицы
     *
     * @return список рейсов
     */
    List<Flight> findAll();

    /**
     * Метод поиска рейса по идентификатору.
     *
     * @param id идентификатор рейса
     * @return искомый рейс
     */
    Flight findById(int id);

    /**
     * Поиск актуального значения идентификатора
     * @return актуальное значение
     * @throws SQLException
     */
    int findActualId() throws SQLException;

}