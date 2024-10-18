package com.denis.vkService.controller;

import com.denis.vkService.dto.UsersRequest;
import com.denis.vkService.service.VkService;
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

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public String getAccessToken(){
        return this.vkService.getAccessTokenForVkRequests();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public String getUsersInfoJsonBy(@RequestBody UsersRequest usersRequest){
        return this.vkService.getUsersBasicInfoFromVkApiBy(
                usersRequest.getStringifyIds(),
                usersRequest.getStringifyFields()
        );
    }
}
