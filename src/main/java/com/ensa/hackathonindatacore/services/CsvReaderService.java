package com.ensa.hackathonindatacore.services;

import com.ensa.hackathonindatacore.entities.Person;

import java.util.List;

public interface CsvReaderService {
    List<String[]> readAllPersons() throws Exception;
    Person createPersonFromCSVRow(String[] row);
}
