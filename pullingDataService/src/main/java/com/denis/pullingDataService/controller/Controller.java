package com.denis.pullingDataService.controller;

import com.denis.pullingDataService.configuration.Config;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pull")
@AllArgsConstructor
public class Controller {
    @Autowired
    private final Config config;

}
