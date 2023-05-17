package ru.croc.javaschool;

import ru.croc.javaschool.deserialize.*;
import ru.croc.javaschool.dsprovider.DerbyDataSourceProvider;
import ru.croc.javaschool.property.PropertyContainer;
import ru.croc.javaschool.repository.impl.DerbyFlightRepository;
import ru.croc.javaschool.service.FlightService;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Demo {
    public static void main(String[] args) throws IOException, ParseException, SQLException {
        PropertyContainer.loadProperties();

        var dataSourceProvider = new DerbyDataSourceProvider();
        var flightRepository = new DerbyFlightRepository(
                dataSourceProvider.getDataSource());
        var flightService = new FlightService(flightRepository);

       /* var task = taskService.createNewTask("Не идти работать в *ндекс");
        System.out.printf(
                "Создана задача: id = %s, наименование = %s%n",
                task.getId(),
                task.getTitle());*/


        File file = new File("src/main/resources/arrivals.xml");
        Deserializer deserializer = new Deserializer();
        Arrivals arrivals = deserializer.doDeserializeArrivals(file);
        file = new File("src/main/resources/departures.xml");
        Departures departures = deserializer.doDeserializeDepartures(file);
       /* System.out.println(arrivals);
        System.out.println(departures);*/


        SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
/*
        System.out.println(date.getTime());
        dateS.setTime(date.getTime());
        System.out.println(dateS);
        System.out.println(date);
*/

        System.out.println(flightService.findId());
        System.out.println(flightService.findAll());

        for (Arrival arrival : arrivals.getArrivals()){
            java.util.Date date = parser.parse(arrival.getDate());
            Timestamp timestamp = new Timestamp(date.getTime());
            flightService.createNewFlight(timestamp,arrival.getCityFrom(),arrival.getBoardRegNumber());
        }
        for (Departure arrival : departures.getDepartures()){
            java.util.Date date = parser.parse(arrival.getDate());
            Timestamp timestamp = new Timestamp(date.getTime());
            flightService.createNewFlight(timestamp,arrival.getCityTo(),arrival.getBoardRegNumber());
        }

        System.out.println(flightService.findAll());
        System.out.println(flightService.findId());




    }
}