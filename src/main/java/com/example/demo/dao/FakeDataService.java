package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository("fakeDao")
public class FakeDataService implements PersonDao{

    private static List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> queryAllPerson() {
        return DB;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> person = selectPersonById(id);
        if(person.isEmpty()) { return 0; }
        DB.remove(person.get());
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, Person updateP) {
        return selectPersonById(id).map(p -> {
            int updated = DB.indexOf(p);
            if (updated >= 0) {
                DB.set(updated, new Person(id, updateP.getName()));
                return 1;
            }
            return 0;
        }).orElse(0);
    }
}
