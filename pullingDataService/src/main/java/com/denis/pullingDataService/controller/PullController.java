package com.denis.pullingDataService.controller;

import com.denis.pullingDataService.dto.UsersResponse;
import com.denis.pullingDataService.service.PullService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pull")
@AllArgsConstructor
public class PullController {
    private PullService pullService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String pullUsers(@RequestParam int fromId, @RequestParam int toId){
        try {
            return this.pullService.pullUsersFromVkServiceByIds(fromId, toId).toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
