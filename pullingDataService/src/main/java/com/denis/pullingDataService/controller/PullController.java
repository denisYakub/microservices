package com.denis.pullingDataService.controller;

import com.denis.pullingDataService.service.PostgreSqlService;
import com.denis.pullingDataService.service.PullService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.HttpRetryException;
import java.util.concurrent.*;

@RestController
@RequestMapping("/api/pull")
@AllArgsConstructor
public class PullController {
    private PullService pullService;
    private PostgreSqlService postgreSqlService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public String pullUsersFromIdToId(@RequestParam int fromId, @RequestParam int toId) {
        return this.pullService.startPulling(fromId, toId).toString();
    }

    @GetMapping("/startingFromFirst")
    @ResponseStatus(HttpStatus.OK)
    public String pullUsersFromFirstOneAndTill(@RequestParam int numberOfUsers){
        return this.pullService.startPulling(numberOfUsers).toString();
    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String getUSer(@PathVariable int id){
        return this.postgreSqlService.getUser(id).toString();
    }
}
