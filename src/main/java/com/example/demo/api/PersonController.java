package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {
    private final PersonService pService;
    @Autowired
    public PersonController(PersonService pService) {
        this.pService = pService;
    }

    @PostMapping
    public void addPerson(@NonNull @RequestBody Person p) {
        pService.addPerson(p);
    }

    @GetMapping
    public List<Person> getPersonList() { return pService.getAllPersons(); }

    @GetMapping(path="{id}")
    public Person getPersonById(@PathVariable("id") UUID id) {
        return pService.getPersonById(id).orElse(null);
    }

    @DeleteMapping(path="{id}")
    public void deletePerson(UUID id) {
        pService.deletePerson(id);
    }

    @PutMapping(path="{id}")
    public void updatePerson(@PathVariable("id") UUID id,
                             @NonNull @RequestBody Person updatedPerson) {
        pService.updatePerson(id, updatedPerson);
    }
}