package com.denis.pullingDataService.controller;

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

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public String downloadUsersFromIdToId(@RequestParam int fromId, @RequestParam int toId) {
        return this.pullService.startDownloadingUsersFromIdToId(fromId, toId).toString();
    }

    @GetMapping("/startingFromFirstOne")
    @ResponseStatus(HttpStatus.OK)
    public String downloadUsersFromFirstOneAndTill(@RequestParam int numberOfUsers){
        return this.pullService.startDownloadingUsers(numberOfUsers).toString();
    }
}
