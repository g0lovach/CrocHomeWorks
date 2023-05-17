package ru.croc.javaschool.deserialize;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import ru.croc.javaschool.deserialize.Arrival;

import java.util.List;

/**
 * Прибытия
 */
@JacksonXmlRootElement
public class Arrivals {
    /**
     * Список прибытий
     */
    @JacksonXmlElementWrapper(localName = "arrivals", useWrapping = false)
    @JacksonXmlProperty(localName = "arrival")
    private List<Arrival> arrivals;

    /**
     * Конструктор
     * @param arrivals
     */
    public Arrivals(List<Arrival> arrivals) {
        this.arrivals = arrivals;
    }

    /**
     * Конструктор
     */
    public Arrivals() {
    }

    /**
     * @return список прибытий
     */
    public List<Arrival> getArrivals() {
        return arrivals;
    }

    @Override
    public String toString() {
        return "Arrivals{" +
                "arrivals=" + arrivals +
                '}';
    }
}
