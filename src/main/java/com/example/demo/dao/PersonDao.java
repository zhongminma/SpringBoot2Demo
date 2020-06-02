package com.example.demo.dao;

import com.example.demo.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {
    int insertPerson(UUID id, Person p);
    default int insertPerson(Person p){
        UUID id = UUID.randomUUID();
        return insertPerson(id, p);
    }
    List<Person> queryAllPerson();
    int deletePersonById(UUID id);
    int updatePersonById(UUID id, Person p);
    Optional<Person> selectPersonById (UUID id);
}



