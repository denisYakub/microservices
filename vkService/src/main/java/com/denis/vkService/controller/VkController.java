package com.denis.vkService.controller;

import com.denis.vkService.service.VkService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vk")
@AllArgsConstructor
public class VkController {
    @Autowired
    private final VkService vkService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void getVkUserInfo(){
        try {
            vkService.getUserInfo();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
