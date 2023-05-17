package ru.croc.javaschool.deserialize;

import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;

/**
 * Десериализатор
 */
public class Deserializer {

    /**
     * десериализация xml прилетов
     * @param file
     * @return
     * @throws IOException
     */
    public Arrivals doDeserializeArrivals(File file) throws IOException {
        JacksonXmlModule module = new JacksonXmlModule();
        XmlMapper xmlMapper = new XmlMapper(module);
        return xmlMapper.readValue(file, Arrivals.class);
    }

    /**
     * десериализация вылетов
     * @param file
     * @return
     * @throws IOException
     */
    public Departures doDeserializeDepartures(File file) throws IOException {
        JacksonXmlModule module = new JacksonXmlModule();
        XmlMapper xmlMapper = new XmlMapper(module);
        return xmlMapper.readValue(file, Departures.class);
    }

}
