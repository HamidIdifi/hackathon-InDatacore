package com.ensa.hackathonindatacore.dtos;

import com.ensa.hackathonindatacore.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class GetPersonResponseDTO {
    private String firstName;
    private String lastName;
    private Gender gender;
}
