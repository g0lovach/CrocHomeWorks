package ru.croc.javaschool.deserialize;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

/**
 * Вылеты
 */
public class Departures {

    /**
     * Список вылетов
     */
    @JacksonXmlElementWrapper(localName = "departures", useWrapping = false)
    @JacksonXmlProperty(localName = "departure")
    private List<Departure> departures;

    /**
     * Конструктор
     * @param departures
     */
    public Departures(List<Departure> departures) {
        this.departures = departures;
    }

    /**
     * Конструктор
     */
    public Departures() {
    }

    /**
     * @return вылеты
     */
    public List<Departure> getDepartures() {
        return departures;
    }

    @Override
    public String toString() {
        return "Departures{" +
                "departures=" + departures +
                '}';
    }
}
