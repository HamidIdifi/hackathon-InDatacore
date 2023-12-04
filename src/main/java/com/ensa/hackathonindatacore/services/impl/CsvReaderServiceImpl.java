package com.ensa.hackathonindatacore.services.impl;

import com.ensa.hackathonindatacore.entities.Person;
import com.ensa.hackathonindatacore.enums.Gender;
import com.ensa.hackathonindatacore.services.CsvReaderService;
import com.opencsv.CSVReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.util.List;

@Service
public class CsvReaderServiceImpl implements CsvReaderService {
    @Override
    public List<String[]> readAllPersons() throws Exception {
        return readAllLines();
    }

    @Override
    public Person createPersonFromCSVRow(String[] row) {
        // Assuming the order of columns in the CSV matches the order in the Person class
        return Person.builder()
                .firstName(row[0])
                .lastName(row[1])
                .phoneNumber(row[2])
                .gender(Gender.valueOf(row[3]))
                .email(row[4])
                .build();
    }

    public List<String[]> readAllLines() throws Exception {
        try (CSVReader csvReader = new CSVReader(new FileReader(new ClassPathResource("personsData.csv").getFile()))) {
            return csvReader.readAll();
        }
    }
}
