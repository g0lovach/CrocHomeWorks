package ru.croc.javaschool;

import ru.croc.javaschool.deserialize.Deserializer;
import ru.croc.javaschool.deserialize.dataclasses.Projects;
import ru.croc.javaschool.serialize.Serializer;

import java.io.File;
import java.io.IOException;
public class Demo {
    public static void main(String[] args) throws IOException {
        Deserializer deserializer = new Deserializer();
        Serializer serializer = new Serializer();
        Projects projects = deserializer.doDeserialize(new File("src/main/resources/test.xml"));
        serializer.doSerialize(projects, new File("src/main/resources/serialized.xml"));
    }

}