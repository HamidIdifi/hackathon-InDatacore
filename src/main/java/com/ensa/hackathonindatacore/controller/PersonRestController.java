package com.ensa.hackathonindatacore.controller;

import com.ensa.hackathonindatacore.dtos.GetPersonResponseDTO;
import com.ensa.hackathonindatacore.dtos.SavePersonRequestDTO;
import com.ensa.hackathonindatacore.entities.Person;
import com.ensa.hackathonindatacore.mappers.PersonMapper;
import com.ensa.hackathonindatacore.services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.ensa.hackathonindatacore.shared.CoreConstant.Pagination.DEFAULT_PAGE_NUMBER;
import static com.ensa.hackathonindatacore.shared.CoreConstant.Pagination.DEFAULT_PAGE_SIZE;

@RestController
@AllArgsConstructor
@RequestMapping("/api/persons")
public class PersonRestController {
    private final PersonService personService;
    private final PersonMapper mapper;

    @GetMapping("/all")
    public ResponseEntity<List<GetPersonResponseDTO>> findAll(@RequestParam(value = "page", defaultValue = "" + DEFAULT_PAGE_NUMBER) Integer page,
                                                              @RequestParam(value = "size", defaultValue = "" + DEFAULT_PAGE_SIZE) Integer size
    ) {
        List<Person> persons = personService.findAllPersons();

        List<GetPersonResponseDTO> dto = persons.stream().map(mapper::toDto).collect(Collectors.toList());
        long totalPeople = personService.countAll();
        int totalPages = (int) Math.ceil((double) totalPeople / size);

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Pages", String.valueOf(totalPages));
        headers.add("Access-Control-Expose-Headers", "X-Total-Pages");

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(dto);


    }

    @PostMapping("/add-person")
    public ResponseEntity<GetPersonResponseDTO> save(@RequestBody SavePersonRequestDTO personDto) {
        Person savedPerson = personService.savePerson(personDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(savedPerson));
    }

    @PostMapping("/add-random-person")
    public ResponseEntity<GetPersonResponseDTO> save() {
        Person savedPerson = personService.saveRandomPerson();
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(savedPerson));
    }

}
