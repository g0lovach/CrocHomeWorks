package ru.croc.javaschool;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.deserialize.Deserializer;
import ru.croc.javaschool.deserialize.dataclasses.Manager;
import ru.croc.javaschool.deserialize.dataclasses.Project;
import ru.croc.javaschool.deserialize.dataclasses.Projects;
import ru.croc.javaschool.deserialize.dataclasses.Specialist;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для тестов десериализатора
 */
public class DeserializerTest {

    /**
     * Проверка десериализации в общем случае
     * @throws IOException
     */
    @Test
    public void doDeserializeBasic() throws IOException {

        Deserializer deserializer = new Deserializer();
        Specialist specialist1 = new Specialist("Человек 3");
        Specialist specialist2 = new Specialist("Человек 4");
        List<Specialist> specialists1 = new ArrayList<>();
        specialists1.add(specialist1);
        specialists1.add(specialist2);
        Specialist specialist3 = new Specialist("Человек 5");
        Specialist specialist4 = new Specialist("Человек 6");
        List<Specialist> specialists2 = new ArrayList<>();
        specialists2.add(specialist3);
        specialists2.add(specialist4);
        Manager manager1 = new Manager("Человек 1", specialists1);
        Manager manager2 = new Manager("Человек 2", specialists2);
        List<Manager> managers1 = new ArrayList<>();
        managers1.add(manager1);
        managers1.add(manager2);
        Project project1 = new Project("Проект 1", "Описание 1", managers1);
        List<Project> projects = new ArrayList<>();
        projects.add(project1);
        Projects projects1 = new Projects(projects);


        Assertions.assertEquals(projects1, deserializer.doDeserialize(new File("src/test/resources/deserializetestfiles/desTest.xml")));

    }
    /**
     * Проверка десериализации в случае, если файл пуст
     * @throws IOException
     */
    @Test
    public void doDeserializeWhenNull() throws IOException {

        Deserializer deserializer = new Deserializer();
        Assertions.assertEquals(null, deserializer.doDeserialize(new File("src/test/resources/deserializetestfiles/desNullTest.xml")));

    }


}
