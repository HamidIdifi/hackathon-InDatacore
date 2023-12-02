package com.ensa.hackathonindatacore.config;

import com.ensa.hackathonindatacore.repositories.PersonRepository;
import com.ensa.hackathonindatacore.services.CsvReaderService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class FileLoader implements CommandLineRunner {
    private final PersonRepository personRepository;
    private final CsvReaderService csvReaderService;


    @Override
    public void run(String... args) throws Exception {
        List<String[]> data = csvReaderService.readAllPersons();
        data.stream()
                .map(csvReaderService::createPersonFromCSVRow)
                .forEach(this.personRepository::save);
    }


}
