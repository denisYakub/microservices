package com.denis.pullingDataService.controller;

import com.denis.pullingDataService.configuration.Config;
import com.denis.pullingDataService.dto.UsersResponse;
import com.denis.pullingDataService.service.PullService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpClient;

@RestController
@RequestMapping("/api/pull")
@AllArgsConstructor
public class PullController {
    @Autowired
    private PullService pullService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String pullUsersFromVkService(@RequestParam int fromId, @RequestParam int toId){
        UsersResponse result = this.pullService.getUsers(fromId, toId);
        return result.toString();
    }
    private JSONPObject covertStringToJSON(String inputString){
        return new JSONPObject(inputString, inputString);
    }
}
