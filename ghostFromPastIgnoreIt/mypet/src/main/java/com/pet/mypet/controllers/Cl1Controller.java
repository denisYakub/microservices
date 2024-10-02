package com.pet.mypet.controllers;

import com.pet.mypet.dto.Cl1Request;
import com.pet.mypet.dto.Cl1Response;
import com.pet.mypet.services.Cl1Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cl1")
@RequiredArgsConstructor
public class Cl1Controller {

    private final Cl1Service cl1Service;

    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    public void createCl1(@RequestBody Cl1Request cl1Request){
        cl1Service.createCl1(cl1Request);
    }

    @GetMapping
    @ResponseStatus (HttpStatus.OK)
    public List<Cl1Response> getAllCl1(){
        return cl1Service.getAllCl1();
    }

    @GetMapping("/hi")
    @ResponseStatus(HttpStatus.OK)
    public String sayHi(){
        return "Hi";
    }
}
