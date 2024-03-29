package com.example.transactionaltest.services;

import com.example.transactionaltest.entities.Person;

public interface PersonAnotherService {

    Long savePerson(Person person);

    Long savePersonWithRollback(Person person);
}
