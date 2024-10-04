package com.denis.vkService.controller;

import com.denis.vkService.service.VkService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vk")
@AllArgsConstructor
public class VkController {
    @Autowired
    private final VkService vkService;

    @GetMapping()
    public String getVkUserInfo(@RequestParam String id){
        try{
            return vkService.getUserInfo(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
