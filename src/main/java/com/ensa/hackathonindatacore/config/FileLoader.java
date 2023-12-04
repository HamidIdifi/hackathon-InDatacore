package com.ensa.hackathonindatacore.config;

import com.ensa.hackathonindatacore.repositories.PersonRepository;
import com.ensa.hackathonindatacore.services.CsvReaderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@AllArgsConstructor
public class FileLoader {
    private final PersonRepository personRepository;
    private final CsvReaderService csvReaderService;


    @PostConstruct
    @Transactional
    public void loadStartupData() throws Exception {
        List<String[]> data = csvReaderService.readAllPersons();
        data.stream()
                .map(csvReaderService::createPersonFromCSVRow)
                .forEach(this.personRepository::save);
    }


}
