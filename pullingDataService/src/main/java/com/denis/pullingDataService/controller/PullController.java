package com.denis.pullingDataService.controller;

import com.denis.pullingDataService.service.PostgresqlService;
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
    private PostgresqlService postgresqlService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public void pullUsersFromIdToId(@RequestParam int fromId, @RequestParam int toId) {
        this.pullService.startPulling(fromId, toId);
    }

    @GetMapping("/startingFromFirst")
    @ResponseStatus(HttpStatus.OK)
    public void pullUsersFromFirstOneAndTill(@RequestParam int numberOfUsers){
        this.pullService.startPulling(numberOfUsers);
    }

    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public void saveUSer(){
        this.postgresqlService.saveUser();
    }
}
