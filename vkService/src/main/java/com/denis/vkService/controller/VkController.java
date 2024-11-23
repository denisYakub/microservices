package com.denis.vkService.controller;

import com.denis.vkService.dto.vkUsersRequest;
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
    public final VkService vkService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public String getAccessToken(){
        return this.vkService.getAccessTokenForVkRequests();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public String getUsersInfoJsonBy(@RequestBody vkUsersRequest vkUsersRequest){
        return this.vkService.getUsersBasicInfoFromVkApiBy(
                vkUsersRequest.getStringifyIds(),
                vkUsersRequest.getStringifyFields()
        );
    }
}
