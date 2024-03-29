package com.example.transactionaltest.services;

import com.example.transactionaltest.TestcontainersConfig;
import com.example.transactionaltest.entities.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@SpringBootTest
@Import(TestcontainersConfig.class)
@Slf4j
class PersonServiceImplTest {

    @Autowired
    private PersonService personService;

    private final List<Person> people = List.of(new Person(null, "John", "Doe"),
            new Person(null, null, "Smith"),
            new Person(null, "Alice", "Johnson"));

    @Test
    void savePeople() {
        personService.savePeople(people);
        log.info("---------------------------------------------------------------------------------------------------------------------------------");
        List<Person> peopleFromDB = personService.findAll();
        peopleFromDB.forEach(p -> log.info("Person from db: " + p.getFirstName() + " " + p.getLastName()));
    }

    @Test
    void savePeopleWithTransactional() {
//        Assertions.assertThrows(RuntimeException.class, () -> personService.savePeopleWithTransactional(people));
        personService.savePeopleWithTransactional(people);
        log.info("---------------------------------------------------------------------------------------------------------------------------------");
        List<Person> peopleFromDB = personService.findAll();
        peopleFromDB.forEach(p -> log.info("Person from db: " + p.getFirstName() + " " + p.getLastName()));
    }

    @Test
    void savePeopleAnotherClass() {
        personService.savePeopleAnotherClass(people);
        log.info("---------------------------------------------------------------------------------------------------------------------------------");
        List<Person> peopleFromDB = personService.findAll();
        peopleFromDB.forEach(p -> log.info("Person from db: " + p.getFirstName() + " " + p.getLastName()));
    }

    @Test
    void savePeopleAnotherClassWithTransactional() {
        Assertions.assertThrows(RuntimeException.class, () -> personService.savePeopleAnotherClassWithTransactional(people));
//        personService.savePeopleAnotherClassWithTransactional(people);
        log.info("---------------------------------------------------------------------------------------------------------------------------------");
        List<Person> peopleFromDB = personService.findAll();
        peopleFromDB.forEach(p -> log.info("Person from db: " + p.getFirstName() + " " + p.getLastName()));
    }

    @Test
    void savePeopleAnotherClassWithTransactionalWithInnerRollback() {
        Assertions.assertThrows(RuntimeException.class, () -> personService.savePeopleAnotherClassWithTransactionalWithInnerRollback(people));
//        personService.savePeopleAnotherClassWithTransactionalWithInnerRollback(people);
        log.info("---------------------------------------------------------------------------------------------------------------------------------");
        List<Person> peopleFromDB = personService.findAll();
        peopleFromDB.forEach(p -> log.info("Person from db: " + p.getFirstName() + " " + p.getLastName()));
    }

    @Test
    void savePeopleAnotherClassWithTransactionalWithOuterRollback() {
        Assertions.assertThrows(RuntimeException.class, () -> personService.savePeopleAnotherClassWithTransactionalWithOuterRollback(people));
//        personService.savePeopleAnotherClassWithTransactionalWithOuterRollback(people);
        log.info("---------------------------------------------------------------------------------------------------------------------------------");
        List<Person> peopleFromDB = personService.findAll();
        peopleFromDB.forEach(p -> log.info("Person from db: " + p.getFirstName() + " " + p.getLastName()));
    }

    @Test
    void savePeopleAnotherClassWithTransactionalWithDoubleRollback() {
        Assertions.assertThrows(RuntimeException.class, () -> personService.savePeopleAnotherClassWithTransactionalWithDoubleRollback(people));
//        personService.savePeopleAnotherClassWithTransactionalWithDoubleRollback(people);
        log.info("---------------------------------------------------------------------------------------------------------------------------------");
        List<Person> peopleFromDB = personService.findAll();
        peopleFromDB.forEach(p -> log.info("Person from db: " + p.getFirstName() + " " + p.getLastName()));
    }
}