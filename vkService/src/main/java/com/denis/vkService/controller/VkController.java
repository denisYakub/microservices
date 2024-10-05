package com.denis.vkService.controller;

import com.denis.vkService.dto.UsersRequest;
import com.denis.vkService.service.VkService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;

@RestController
@RequestMapping("/api/vk")
@AllArgsConstructor
public class VkController {
    @Autowired
    private final VkService vkService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public String getVkUserInfo(@RequestBody UsersRequest usersRequest){
        try{
            return vkService.getUserInfo(usersRequest.getStringifyIds(), usersRequest.getStringifyFields());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/codeUri")
    @ResponseStatus(HttpStatus.OK)
    public String getAccessTokenForVkApiRequests(){
        try{
            return vkService.getCode();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
