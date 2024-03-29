package com.example.transactionaltest.services;

import com.example.transactionaltest.entities.Person;

import java.util.List;

public interface PersonService {

    List<Person> findAll();

    Long savePerson(Person person);

    List<Long> savePeople(List<Person> people);

    List<Long> savePeopleWithTransactional(List<Person> people);

    List<Long> savePeopleAnotherClass(List<Person> people);

    List<Long> savePeopleAnotherClassWithTransactional(List<Person> people);

    List<Long> savePeopleAnotherClassWithTransactionalWithInnerRollback(List<Person> people);

    List<Long> savePeopleAnotherClassWithTransactionalWithOuterRollback(List<Person> people);

    List<Long> savePeopleAnotherClassWithTransactionalWithDoubleRollback(List<Person> people);
}
