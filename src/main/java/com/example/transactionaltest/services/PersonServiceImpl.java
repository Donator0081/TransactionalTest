package com.example.transactionaltest.services;

import com.example.transactionaltest.entities.Person;
import com.example.transactionaltest.repositories.PersonRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonAnotherService personAnotherService;

    @Override
    public List<Person> findAll() {
        return (List<Person>) personRepository.findAll();
    }

    @Override
    @Transactional
    public Long savePerson(Person person) {
        if (person.getFirstName() == null){
            throw new RuntimeException("ALARM!!!!! Exception!!!!");
        }
        return personRepository.save(person).getId();
    }

    @Override
    public List<Long> savePeople(List<Person> people) {
        List<Long> savedPeople = new ArrayList<>();
        for (Person person : people) {
            try {
                log.info("Save {}", person.getFirstName());
                savedPeople.add(savePerson(person));
                log.info("{} saved", person.getFirstName());
            } catch (RuntimeException e) {
                log.info(e.getMessage());
            }
        }
        return savedPeople;
    }

    @Override
    @Transactional
    public List<Long> savePeopleWithTransactional(List<Person> people) {
        List<Long> savedPeople = new ArrayList<>();
        for (Person person : people) {
            try {
                log.info("Save {}", person.getFirstName());
                savedPeople.add(savePerson(person));
                log.info("{} saved", person.getFirstName());
            } catch (RuntimeException e) {
                log.info(e.getMessage());
            }
        }
        return savedPeople;
    }

    @Override
    public List<Long> savePeopleAnotherClass(List<Person> people) {
        List<Long> savedPeople = new ArrayList<>();
        for (Person person : people) {
            try {
                log.info("Save {}", person.getFirstName());
                savedPeople.add(personAnotherService.savePerson(person));
                log.info("{} saved", person.getFirstName());
            } catch (RuntimeException e) {
                log.info(e.getMessage());
            }
        }
        return savedPeople;
    }

    @Override
    @Transactional
    public List<Long> savePeopleAnotherClassWithTransactional(List<Person> people) {
        List<Long> savedPeople = new ArrayList<>();
        for (Person person : people) {
            try {
                log.info("Save {}", person.getFirstName());
                savedPeople.add(personAnotherService.savePerson(person));
                log.info("{} saved", person.getFirstName());
            } catch (RuntimeException e) {
                log.info(e.getMessage());
            }
        }
        return savedPeople;
    }

    @Override
    @Transactional(dontRollbackOn = RuntimeException.class)
    public List<Long> savePeopleAnotherClassWithTransactionalWithInnerRollback(List<Person> people) {
        List<Long> savedPeople = new ArrayList<>();
        for (Person person : people) {
            try {
                log.info("Save {}", person.getFirstName());
                savedPeople.add(personAnotherService.savePerson(person));
                log.info("{} saved", person.getFirstName());
            } catch (RuntimeException e) {
                log.info(e.getMessage());
            }
        }
        return savedPeople;
    }

    @Override
    @Transactional
    public List<Long> savePeopleAnotherClassWithTransactionalWithOuterRollback(List<Person> people) {
        List<Long> savedPeople = new ArrayList<>();
        for (Person person : people) {
            try {
                log.info("Save {}", person.getFirstName());
                savedPeople.add(personAnotherService.savePersonWithRollback(person));
                log.info("{} saved", person.getFirstName());
            } catch (RuntimeException e) {
                log.info(e.getMessage());
            }
        }
        return savedPeople;
    }

    @Override
    @Transactional(dontRollbackOn = RuntimeException.class)
    public List<Long> savePeopleAnotherClassWithTransactionalWithDoubleRollback(List<Person> people) {
        List<Long> savedPeople = new ArrayList<>();
        for (Person person : people) {
            log.info("Save {}", person.getFirstName());
            savedPeople.add(personAnotherService.savePersonWithRollback(person));
            log.info("{} saved", person.getFirstName());
        }
        return savedPeople;
    }
}
