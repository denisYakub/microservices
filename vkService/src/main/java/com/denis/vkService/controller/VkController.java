package com.denis.vkService.controller;

import com.denis.vkService.dto.UsersRequest;
import com.denis.vkService.dto.UsersResponse;
import com.denis.vkService.service.VkService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vk")
@AllArgsConstructor
public class VkController {
    @Autowired
    private final VkService vkService;

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public String getVkUsersInfo(@RequestBody UsersRequest usersRequest){
        try{
            return vkService.getUsersInfo(usersRequest.getStringifyIds(), usersRequest.getStringifyFields());
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
