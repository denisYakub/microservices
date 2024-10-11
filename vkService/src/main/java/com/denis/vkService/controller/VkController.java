package com.denis.vkService.controller;

import com.denis.vkService.dto.UsersRequest;
import com.denis.vkService.service.VkService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vk")
@AllArgsConstructor
public class VkController {
    private final VkService vkService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public String getAccessTokenForVkApiRequests(){
        //Переходим по ссылке
        //Нажимаем войти как денис
        // Из поисковой строки копируем ACCESS_TOKEN
        // Заменяем его в .env
        return this.vkService.getAccessTokenForEnv();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public String getVkUsersInfo(@RequestBody UsersRequest usersRequest){
        return this.vkService.getUsersBasicInfo(
                usersRequest.getStringifyIds(),
                usersRequest.getStringifyFields()
        );
    }
}
