package com.denis.sortAndAnalyzeService.controller;

import com.denis.sortAndAnalyzeService.service.AnalyzeService;
import com.denis.sortAndAnalyzeService.service.SortService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutorService;

@RestController
@RequestMapping("/api/sortAnalyze")
@AllArgsConstructor
public class SortAndAnalyzeController {
    @Autowired
    public SortService sortService;
    @Autowired
    public AnalyzeService analyzeService;

    @PostMapping("/{from}/{till}")
    @ResponseStatus(HttpStatus.OK)
    public String findBots(@PathVariable int from, @PathVariable int till){
        try {
            return this.analyzeService.UseMultiThreadToAnalyze(from, till);
        } catch (Exception e) {
            return "threads error";
        }
    }

    @DeleteMapping("/cleanUsersWithClosedAcc")
    @ResponseStatus(HttpStatus.OK)
    public void deleteClosedUsers(){
        this.sortService.deleteUsersWithClosedAccount();
    }
}
