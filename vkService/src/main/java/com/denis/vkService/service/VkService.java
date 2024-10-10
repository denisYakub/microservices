package com.denis.vkService.service;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class VkService {
    private final int APP_ID = 52411376;
    private String ACCESS_TOKEN = null;

    private final HttpClient client = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

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

    public String getUsersBasicInfo(String ids, String fields, String accessToken){
        try {
            return this.getUsersInfoJSON(ids, fields, accessToken);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public void setAccessToken(String accessToken){
        if(isValidAccessToken(accessToken)) {
            this.ACCESS_TOKEN = accessToken;
        }else{
            throw new RuntimeException("Access token isn't valid");
        }
    }

    private boolean isValidAccessToken(String accessToken){
        try {
            this.getUsersBasicInfo("1", "", accessToken);
            return true;
        }catch (RuntimeException e){
            return false;
        }
    }

    private String getUsersInfoJSON(String userIds, String fields, String accessToken) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(
                        URI.create("https://api.vk.com/method/users.get" +
                                "?access_token=" + accessToken +
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