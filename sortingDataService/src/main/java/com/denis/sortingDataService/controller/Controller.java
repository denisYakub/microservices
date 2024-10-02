package com.denis.sortingDataService.controller;

import com.denis.sortingDataService.dto.PersonRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sort")
public class Controller {
    @GetMapping("/hi")
    @ResponseStatus(HttpStatus.OK)
    public String sayHi(){
        return "Hi";
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void acceptListOfPersons(@RequestBody PersonRequest... personRequests){
        for(var person: personRequests){
            System.out.println(person.id() + " " + person.name());
        }
    }
}
