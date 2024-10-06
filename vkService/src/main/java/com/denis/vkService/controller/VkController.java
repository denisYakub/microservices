package com.denis.vkService.controller;

import com.denis.vkService.dto.UsersRecord;
import com.denis.vkService.dto.UsersRequest;
import com.denis.vkService.service.VkService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;

@RestController
@RequestMapping("/api/vk")
@AllArgsConstructor
public class VkController {
    private final VkService vkService;

    @GetMapping("/accessToken")
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
        try {
            return this.vkService.getUsersInfoJSON(
                    usersRequest.getStringifyIds(),
                    usersRequest.getStringifyFields()
            );
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/test/getRecord")
    @ResponseStatus(HttpStatus.OK)
    public UsersRecord getVkUsersInfoTest(@RequestBody UsersRequest usersRequest){
        try {
            return this.vkService.getUsersInfoRecord(
                    usersRequest.getStringifyIds(),
                    usersRequest.getStringifyFields()
            );
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
