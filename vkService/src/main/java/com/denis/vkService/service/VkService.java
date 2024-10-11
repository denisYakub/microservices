package com.denis.vkService.service;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class VkService {
    private final HttpClient client = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    @Value("${global.APP_ID}")
    private int APP_ID;
    @Value("${global.ACCESS_TOKEN}")
    private String ACCESS_TOKEN;

    public String getAccessTokenForEnv() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(
                        URI.create("https://oauth.vk.com/authorize" +
                                "?response_type=token" +
                                "&client_id=" + this.APP_ID +
                                "&state=photos,groups" +
                                "&scope=offline" +
                                "display=page" +
                                "&v=5.199")
                ).GET().build();

        //TODO заменить на записывание токена в поле ACCESS_TOKEN
        return request.uri().toString();
    }

    public String getUsersBasicInfo(String ids, String fields){
        try {
            return this.getUsersInfoJSON(ids, fields);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    private boolean isValidAccessToken(){
        try {
            this.getUsersBasicInfo("1", "");
            return true;
        }catch (RuntimeException e){
            return false;
        }
    }

    private String getUsersInfoJSON(String userIds, String fields) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(
                        URI.create("https://api.vk.com/method/users.get" +
                                "?access_token=" + this.ACCESS_TOKEN +
                                "&user_ids=" + userIds +
                                "&fields=" + fields +
                                "&v=5.199")
                ).GET().build();

        var response = this.client.send(request, HttpResponse.BodyHandlers.ofString()).body();

        if(response.contains("error_msg")){
            throw new RuntimeException(response);
        }

        return response;
    }
}