package ru.croc.javaschool;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.deserialize.Deserializer;
import ru.croc.javaschool.deserialize.dataclasses.Manager;
import ru.croc.javaschool.deserialize.dataclasses.Project;
import ru.croc.javaschool.deserialize.dataclasses.Projects;
import ru.croc.javaschool.deserialize.dataclasses.Specialist;
import ru.croc.javaschool.serialize.Serializer;
import ru.croc.javaschool.serialize.dataclasses.Participation;
import ru.croc.javaschool.serialize.dataclasses.Person;
import ru.croc.javaschool.serialize.dataclasses.Persons;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс проверки сериализатора
 */
public class SerializerTest {


    /**
     * проверка сериализации в общем случае
     * @throws IOException
     */
@Test
    public void doSerializeBasic() throws IOException {
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


    Serializer serializer = new Serializer();
    serializer.doSerialize(projects1,
            new File("src/test/resources/serializetestfiles/serializedTest.xml"));
    Assertions.assertArrayEquals(
            Files.readAllBytes(new File("src/test/resources/serializetestfiles/serializedTest.xml").toPath()),
            Files.readAllBytes(new File("src/test/resources/serializetestfiles/serializedCorrect.xml").toPath()));
}

    /**
     * Проверка сериализации, когда сериализуемый объект null
     * @throws IOException
     */
    @Test
    public void doSerializeWhenNull() throws IOException {
        Serializer serializer = new Serializer();

            serializer.doSerialize(null,
                    new File("src/test/resources/serializetestfiles/serializedTestNull.xml"));
            Assertions.assertEquals(new File("src/test/resources/serializetestfiles/serializedTestNull.xml").exists(),
                    false);
        }

    /**
     * Проверка метода prepare на корректность работы в общем случае
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
        @Test
    public void prepareTestBasic() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
            Specialist specialist1 = new Specialist("Человек 3");
            List<Specialist> specialists1 = new ArrayList<>();
            specialists1.add(specialist1);
            Manager manager1 = new Manager("Человек 1", specialists1);
            List<Manager> managers1 = new ArrayList<>();
            managers1.add(manager1);
            Project project1 = new Project("Проект 1", "Описание 1", managers1);
            List<Project> projects = new ArrayList<>();
            projects.add(project1);
            Projects projects1 = new Projects(projects);


            Participation participation1 = new Participation("Проект 1", "Специалист", "Человек 1");
            Participation participation2 = new Participation("Проект 1", "Менеджер", "");
            Person person1 = new Person("Человек 1");
            Person person2 = new Person("Человек 3");
            person1.addParticipation(participation2);
            person2.addParticipation(participation1);
            Persons persons = new Persons();


            Serializer serializer = new Serializer();


            Method method = serializer.getClass().getDeclaredMethod("prepare", Projects.class);
            method.setAccessible(true);
            Object obj =  method.invoke(serializer ,projects1);

            List<Person> people = new ArrayList<>();
            people.add(person1);
            people.add(person2);

            persons.setPeople(people);


            Assertions.assertEquals(serializer.getPeople(), persons );
        }


    /**
     * Проверка метода prepare на корректность работы в случае передачи ему значения null
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Test
    public void prepareTestWhenNull() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Serializer serializer = new Serializer();
        Method method = serializer.getClass().getDeclaredMethod("prepare", Projects.class);
        method.setAccessible(true);
        Object obj =  method.invoke(serializer , (Object) null);


        Assertions.assertNull(serializer.getPeople());
    }

}
