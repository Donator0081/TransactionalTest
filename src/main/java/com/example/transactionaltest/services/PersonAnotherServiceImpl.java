package com.example.transactionaltest.services;

import com.example.transactionaltest.entities.Person;
import com.example.transactionaltest.repositories.PersonRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonAnotherServiceImpl implements PersonAnotherService {

    private final PersonRepository personRepository;

    @Override
    @Transactional
    public Long savePerson(Person person) {
        return personRepository.save(person).getId();
    }

    @Override
    @Transactional(dontRollbackOn = RuntimeException.class)
    public Long savePersonWithRollback(Person person) {
        if (person.getFirstName() == null) {
            throw new RuntimeException("ALARM!!!!! Exception!!!!");
        }
        return personRepository.save(person).getId();
    }
}
