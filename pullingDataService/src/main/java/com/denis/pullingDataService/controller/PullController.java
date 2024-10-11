package com.denis.pullingDataService.controller;

import com.denis.pullingDataService.service.PostgresqlService;
import com.denis.pullingDataService.service.PullService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.HttpRetryException;
import java.util.concurrent.*;

@RestController
@RequestMapping("/api/pull")
@AllArgsConstructor
public class PullController {
    private final PullService pullService;

    @GetMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void pullUsersFromIdToId(@RequestParam int fromId, @RequestParam int toId) {
        this.pullService.startPulling(fromId, toId);
    }

    @GetMapping("/{numberOfUsers}")
    @ResponseStatus(HttpStatus.CREATED)
    public void pullUsersFromFirstOneAndTill(@PathVariable int numberOfUsers){
        this.pullService.startPulling(0, numberOfUsers);
    }
}
