package com.ensa.hackathonindatacore.services;

import com.ensa.hackathonindatacore.dtos.GetPersonResponseDTO;
import com.ensa.hackathonindatacore.dtos.SavePersonRequestDTO;
import com.ensa.hackathonindatacore.entities.Person;
import com.ensa.hackathonindatacore.enums.Gender;
import com.ensa.hackathonindatacore.mappers.PersonMapper;
import com.ensa.hackathonindatacore.repositories.PersonRepository;
import com.ensa.hackathonindatacore.services.impl.PersonServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {
    @Mock
    private PersonRepository personRepository;
    @Mock
    private PersonMapper personMapper;
    @InjectMocks
    private PersonServiceImpl personService;

    @Test
    public void givenTwoPersonsInDatabase_whenFindAllPersons_thenReturnListOfAllPersons() {
        // Given
        Person person1 = Person.builder().id(1L).firstName("John").build();
        Person person2 = Person.builder().id(2L).firstName("Jane").build();
        List<Person> persons = Arrays.asList(person1, person2);

        Mockito.when(personRepository.findAll()).thenReturn(persons);

        // When
        List<Person> result = personService.findAllPersons();

        // Then
        verify(personRepository, times(1)).findAll();
        assert (result.size() == 2);
    }
    @Test
    public void givenSavePersonRequestDTO_whenSavePerson_thenPersonIsSavedSuccessfully() {
        // Given
        SavePersonRequestDTO personDTO = SavePersonRequestDTO.builder().firstName("John").build();
        Person personToSave = Person.builder().firstName("John").build();
        Mockito.when(personMapper.toEntity(personDTO)).thenReturn(personToSave);
        Mockito.when(personRepository.save(any(Person.class))).thenReturn(personToSave);

        // When
        Person savedPerson = personService.savePerson(personDTO);

        // Then
        verify(personRepository, times(1)).save(any(Person.class));
        assert(savedPerson != null);
        assert(savedPerson.getFirstName().equals("John"));
    }

}
