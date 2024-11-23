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
    private SortService sortService;
    @Autowired
    private AnalyzeService analyzeService;

    @DeleteMapping("/cleanUsersWithClosedAcc")
    @ResponseStatus(HttpStatus.OK)
    public void deleteClosedUsers(){
        this.sortService.deleteUsersWithClosedAccount();
    }
}
