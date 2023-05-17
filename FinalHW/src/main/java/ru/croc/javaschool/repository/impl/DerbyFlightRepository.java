package ru.croc.javaschool.repository.impl;



import ru.croc.javaschool.models.Flight;
import ru.croc.javaschool.repository.FlightRepository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Реализация репозитория рейсов для Derby.
 */
public class DerbyFlightRepository implements FlightRepository {

    private final DataSource dataSource;

    public DerbyFlightRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        initTable();
    }

    /**
     * инициализация таблицы
     */
    @Override
    public void initTable() {
        System.out.println("Инициализация таблицы: " + Flight.TABLE_NAME);
        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            var databaseMetadata = connection.getMetaData();
            var resultSet = databaseMetadata.getTables(
                    null,
                    null,
                    // Несмотря на то, что мы создаем таблицу в нижнем регистре (и дальше к ней так же обращаемся),
                    // поиск мы осуществляем в верхнем. Такие вот приколы
                    Flight.TABLE_NAME.toUpperCase(),
                    new String[]{"TABLE"});
            if (resultSet.next()) {
                System.out.println("Таблица уже существует");
            } else {
                statement.executeUpdate(
                        "CREATE TABLE " + Flight.TABLE_NAME + " (" + "id INTEGER PRIMARY KEY, " + "date TIMESTAMP, " + "otherCity VARCHAR(50), " + "boardRegNumber VARCHAR(50)" + ")");
                System.out.println("Таблица успешно создана");
            }
        } catch (SQLException e) {
            System.out.println("Возникла ошибка при создании таблицы: " + e.getMessage());
        } finally {
            System.out.println("=========================");
        }
    }


    /**
     * создание новой записи
     * @param flight
     * @return
     */
     @Override
     public boolean create(Flight flight) {
         var query = "INSERT INTO " + Flight.TABLE_NAME + " VALUES (?, ?, ?, ?)";
         int id = 0;

         try (var connection = dataSource.getConnection();
              var statement = connection.prepareStatement(query)) {
             statement.setString(
                     1,
                     Integer.toString(flight.getId()));
             statement.setString(
                     2,
                     flight.getDate().toString());
             statement.setString(
                     3,
                     flight.getOtherCity());
             statement.setString(
                     4,
                     flight.getBoardRegNumber());
             statement.execute();
         } catch (SQLException e) {
             System.out.println("Возникла ошибка выполнения запроса (создание): " + e.getMessage());
             return false;
         }

         return true;
     }

    /**
     *
     * @return выборка всех записей
     */
    @Override
    public List<Flight> findAll() {
        var flights = new ArrayList<Flight>();
        var flight = (Flight) null;
        var query = String.format(
                "SELECT id, date, otherCity, boardRegNumber FROM %s ",
                Flight.TABLE_NAME);

        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement();
             var resultSet = statement.executeQuery(query)) {
            while(resultSet.next()) {
                flights.add(new Flight(

                      Integer.parseInt(resultSet.getString("id")),
                        resultSet.getTimestamp("date"),
                        resultSet.getString("otherCity"),
                        resultSet.getString("boardRegNumber")));
            }
        } catch (SQLException e) {
            System.out.println("Возникла ошибка выполнения запроса (выборка всех): " + e.getMessage());
        }

        return flights;
    }

    /**
     * поиск по id
     * @param id
     * @return запись
     */
    @Override
    public Flight findById(int id) {
        var flight = (Flight) null;
        var query = String.format(
                "SELECT id, date, otherCity, boardRegNumber FROM %s WHERE id = '%s'",
                Flight.TABLE_NAME,
                id);

        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement();
             var resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                flight = new Flight(

                             Integer.parseInt( resultSet.getString("id")),
                        resultSet.getTimestamp("date"),
                        resultSet.getString("otherCity"),
                        resultSet.getString("boardRegNumber"));
            }
        } catch (SQLException e) {
            System.out.println("Возникла ошибка выполнения запроса (поиск): " + e.getMessage());
        }

        return flight;
    }

    /**
     * @return актуальное значение id
     * @throws SQLException
     */
    @Override
    public int findActualId() throws SQLException {
        var query = String.format(
                "SELECT COUNT(id) FROM %s ",
                Flight.TABLE_NAME);
        try (var connection = dataSource.getConnection();
        var statement = connection.createStatement();
        var resultSet = statement.executeQuery(query)){
            if (resultSet.next()) {
               return resultSet.getInt(1) + 1;
            }
        }
        catch (SQLException e) {
            System.out.println("Возникла ошибка выполнения запроса (поиск акуального id): " + e.getMessage());
        }
        return -1;
    }


}