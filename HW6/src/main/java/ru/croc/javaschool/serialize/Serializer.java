package ru.croc.javaschool.serialize;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import ru.croc.javaschool.deserialize.dataclasses.Manager;
import ru.croc.javaschool.deserialize.dataclasses.Project;
import ru.croc.javaschool.deserialize.dataclasses.Projects;
import ru.croc.javaschool.deserialize.dataclasses.Specialist;
import ru.croc.javaschool.serialize.dataclasses.Participation;
import ru.croc.javaschool.serialize.dataclasses.Person;
import ru.croc.javaschool.serialize.dataclasses.Persons;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Сериализатор в рамках задачи
 */
public class Serializer {
    /**
     * Список людей
     */
   private Persons people;

    /**
     * Составление ифнормации о людях на основе информации о проектах
     * @param projects - проекты
     */
    private void prepare(Projects projects){
         ArrayList<Person> persons = new ArrayList<>();
         if(projects == null){
             people = null;
             return;
         }
        for (Project project: projects.getProjects()){

            for(Manager manager : project.getManagers()){
                boolean contains = false;
                for (Person person:persons){
                    if (manager.getName().equals(person.getName())){
                        person.addParticipation(new Participation(project.getTitle(), "Менеджер", ""));
                        contains = true;
                    }
                }
                if(!contains){
                    Person tmp = new Person(manager.getName());
                    tmp.addParticipation(new Participation(project.getTitle(), "Менеджер", ""));
                    persons.add(tmp);
                }
                for (Specialist specialist: manager.getSpecialists()){
                     contains = false;
                    for (Person person:persons){
                        if (specialist.getName().equals(person.getName())){
                            person.addParticipation(new Participation(project.getTitle(), "Специалист", manager.getName()));
                            contains = true;
                        }
                    }
                    if(!contains){
                        Person tmp = new Person(specialist.getName());
                        tmp.addParticipation(new Participation(project.getTitle(), "Специалист", manager.getName()));
                        persons.add(tmp);
                    }
                }
            }

        }
        people.setPeople( persons);

    }


    /**
     * Конструктор
     */
    public Serializer(){
         this.people = new Persons();
    }

    /**
     * Сериализация
     * @param projects - информация о проектах
     * @param file - объект файла, в который была помещена информацуия
     */
    public void doSerialize(Projects projects, File file){
        prepare(projects);
        if(people == null){
            return;
        }
        try {
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.registerModule(new JaxbAnnotationModule());
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
            xmlMapper.setDefaultUseWrapper(false);


            String xmlString = xmlMapper.writeValueAsString(people);


            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(xmlString);

            fileWriter.close();
        } catch (IOException e) {
            return;
        }
    }

    /**
     * @return список людей
     */
    public Persons getPeople() {
        return people;
    }
}
