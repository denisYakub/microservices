package com.denis.sortAndAnalyzeService.controller;

import com.denis.sortAndAnalyzeService.service.AnalyzeService;
import com.denis.sortAndAnalyzeService.service.SortService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sortAnalyze")
@AllArgsConstructor
public class SortAndAnalyzeController {
    @Autowired
    public SortService sortService;
    @Autowired
    public AnalyzeService analyzeService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String getUser(@PathVariable int id){
        return this.analyzeService.getUserEntityById(id).toString();
    }

    @PostMapping("/checkForBots/{numberOfUsers}")
    @ResponseStatus(HttpStatus.OK)
    public int[] findBots(@PathVariable int numberOfUsers){
        return this.analyzeService.analyzeUsersForBots(numberOfUsers).stream().mapToInt(Integer::intValue).toArray();
    }

    @DeleteMapping("/cleanUsersWithClosedAcc")
    @ResponseStatus(HttpStatus.OK)
    public void deleteClosedUsers(){
        this.sortService.deleteUsersWithClosedAccount();
    }
}
