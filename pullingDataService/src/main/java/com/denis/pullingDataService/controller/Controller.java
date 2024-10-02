package com.denis.pullingDataService.controller;

import com.denis.pullingDataService.configuration.Config;
import com.denis.pullingDataService.dto.PersonRequest;
import com.denis.pullingDataService.model.Person;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/pull")
@AllArgsConstructor
public class Controller {
    @Autowired
    private final Config config;

    private final String URI_SORTING_SERVICE = "http://localhost:8081/api/sort";

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String sayHelloFromS1(){

        var resultFromConfig = config.restTemplate()
                .getForEntity(this.URI_SORTING_SERVICE + "/hi", String.class);

        return "S1 saying: " + resultFromConfig.getBody();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void sendPersonToS1(@RequestBody PersonRequest... personRequest){

        List<Person> personRespondList = new ArrayList<>();
        int id = 0;

        for(var person: personRequest){
            personRespondList.add(Person.builder()
                            .id(id++)
                            .name(person.name())
                            .build());
        }

        config.restTemplate().put(this.URI_SORTING_SERVICE, personRespondList);
    }
}
