package ru.croc.javaschool.deserialize;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.croc.javaschool.deserialize.dataclasses.Projects;

import java.io.File;
import java.io.IOException;

/**
 * Класс для десериализации объектов в рамках задачи
 */
public class Deserializer {

    /**
     * Метод, выполняющий десериализацию из xml представления в объект {@link Projects}
     * @param file - объект xml - файла
     * @return  - объект {@link Projects}
     * @throws IOException
     */
   public Projects doDeserialize(File file) throws IOException{
       try {
           JacksonXmlModule module = new JacksonXmlModule();
           XmlMapper xmlMapper = new XmlMapper(module);
           return xmlMapper.readValue(file, Projects.class);
       }
       catch (IOException ioException){
           return null;
       }
    }

}
