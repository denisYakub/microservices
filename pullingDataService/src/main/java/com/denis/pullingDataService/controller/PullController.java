package com.denis.pullingDataService.controller;

import com.denis.pullingDataService.service.PullService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.*;

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
    //TODO доработать метод или обьеденить с верхним
    @GetMapping("/download")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String downloadUsers(@RequestParam int numberOfUsers){
        try {
            return this.pullService.startDownloadingUsers(numberOfUsers);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
