package com.ensa.hackathonindatacore.services;

import com.ensa.hackathonindatacore.entities.Person;
import com.ensa.hackathonindatacore.enums.Gender;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class CsvReaderServiceImpl implements CsvReaderService {
    @Override
    public List<String[]> readAllPersons() throws Exception {
        Path path = Paths.get(
                ClassLoader.getSystemResource("personsData.csv").toURI());
        return readAllLines(path);
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

    public List<String[]> readAllLines(Path filePath) throws Exception {
        try (Reader reader = Files.newBufferedReader(filePath)) {
            try (CSVReader csvReader = new CSVReader(reader)) {
                return csvReader.readAll();
            }
        }
    }
}
