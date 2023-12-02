package com.ensa.hackathonindatacore.mappers;

import com.ensa.hackathonindatacore.dtos.GetPersonResponseDTO;
import com.ensa.hackathonindatacore.dtos.SavePersonRequestDTO;
import com.ensa.hackathonindatacore.entities.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {
    public GetPersonResponseDTO toDto(Person person) {
        return GetPersonResponseDTO.builder()
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .gender(person.getGender())
                .build();
    }

    public Person toEntity(SavePersonRequestDTO dto) {
        return Person.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .gender(dto.getGender())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .build();
    }
}
