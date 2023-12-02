package com.ensa.hackathonindatacore.dtos;

import com.ensa.hackathonindatacore.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SavePersonRequestDTO {
    private String firstName;
    private String lastName;
    private Gender gender;
    private String phoneNumber;
    private String email;
}
