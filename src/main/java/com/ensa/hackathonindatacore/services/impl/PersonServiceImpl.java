package com.ensa.hackathonindatacore.services.impl;

import com.ensa.hackathonindatacore.dtos.SavePersonRequestDTO;
import com.ensa.hackathonindatacore.entities.Person;
import com.ensa.hackathonindatacore.enums.Gender;
import com.ensa.hackathonindatacore.exceptions.BusinessException;
import com.ensa.hackathonindatacore.exceptions.ElementAlreadyExistException;
import com.ensa.hackathonindatacore.exceptions.ElementNotFoundException;
import com.ensa.hackathonindatacore.mappers.PersonMapper;
import com.ensa.hackathonindatacore.repositories.PersonRepository;
import com.ensa.hackathonindatacore.services.PersonService;
import com.ensa.hackathonindatacore.shared.CoreConstant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper mapper;

    @Override
    public Person findPersonById(Long personId) throws ElementNotFoundException {
        final Optional<Person> personExist = personRepository.findById(personId);
        if (personExist.isPresent()) {
            return personExist.get();
        } else {
            log.warn(CoreConstant.Exception.NOT_FOUND);
            throw new ElementNotFoundException(null, new ElementNotFoundException(), CoreConstant.Exception.NOT_FOUND, new Object[]{personId});
        }

    }

    @Override
    public List<Person> findAllPersons() {
        try {
            return personRepository.findAll();
        } catch (BusinessException e) {
            throw new BusinessException(null, e, CoreConstant.Exception.FIND_ELEMENTS, null);
        }

    }

    @Override
    public Person savePerson(SavePersonRequestDTO dto) throws ElementAlreadyExistException {
        final Optional<Person> personExist = personRepository.findByEmail(dto.getEmail());
        if (personExist.isEmpty()) {
            Person person = mapper.toEntity(dto);
            return personRepository.save(person);
        } else {
            log.warn(CoreConstant.Exception.ALREADY_EXISTS);
            throw new ElementAlreadyExistException(null, new ElementAlreadyExistException(), CoreConstant.Exception.ALREADY_EXISTS, null);
        }

    }

    @Override
    public Person saveRandomPerson() throws ElementAlreadyExistException {
        Person person = Person.builder()
                .firstName(generateRandomString())
                .lastName(generateRandomString())
                .gender(generateRandomGender())
                .phoneNumber(generateRandomPhoneNumber())
                .email(generateRandomEmail())
                .build();
        return personRepository.save(person);
    }

    @Override
    public long countAll() {
        return personRepository.count();
    }


    private String generateRandomString() {
        return "RandomString";
    }

    private String generateRandomPhoneNumber() {
        return "1234567890";
    }

    private Gender generateRandomGender() {
        return Math.random() < 0.5 ? Gender.MALE : Gender.FEMALE;
    }

    private String generateRandomEmail() {
        return "random.email@example.com";
    }
}
