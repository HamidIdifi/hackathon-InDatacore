package com.ensa.hackathonindatacore.services;

import com.ensa.hackathonindatacore.dtos.SavePersonRequestDTO;
import com.ensa.hackathonindatacore.entities.Person;
import com.ensa.hackathonindatacore.exceptions.ElementAlreadyExistException;
import com.ensa.hackathonindatacore.exceptions.ElementNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PersonService {
    Person findPersonById(Long personId) throws ElementNotFoundException;
    List<Person> findAllPersons();
    Person savePerson(SavePersonRequestDTO dto) throws ElementAlreadyExistException;
    Person saveRandomPerson() throws ElementAlreadyExistException;
    public long countAll();
}
