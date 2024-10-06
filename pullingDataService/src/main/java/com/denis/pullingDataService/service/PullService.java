package com.denis.pullingDataService.service;

import com.denis.pullingDataService.configuration.Config;
import com.denis.pullingDataService.dto.UsersRequest;
import com.denis.pullingDataService.dto.UsersResponse;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;

@Service
@AllArgsConstructor
public class PullService {
    private final String URI_VK_SERVICE = "http://localhost:80/api/vk";

    @Autowired
    private final Config config;

    private final Gson gson = new Gson();

    public UsersResponse getUsers(int from, int to){
        int sizeOfIdsArray = to - from + 1;
        int[] ids = new int[sizeOfIdsArray];
        int valueOfIdsArray = from;
        for(int i = 0; i < sizeOfIdsArray; i++){
            ids[i] = valueOfIdsArray++;
        }
        String[] fields = {""};
        String response = config.restTemplate()
                .postForEntity(this.URI_VK_SERVICE, new UsersRequest(ids, fields), String.class)
                .getBody();

        return gson.fromJson(response, UsersResponse.class);
    }

}
