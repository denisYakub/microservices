package com.denis.vkService.service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@RequiredArgsConstructor
public class VkService {
    @Autowired
    private final Gson gson;

    private final HttpClient client = HttpClient.newHttpClient();

    @Value("${global.ACCESS_TOKEN}")
    private String ACCESS_TOKEN;
    @Value("${global.APP_ID}")
    private int APP_ID;

    public String getAccessTokenForVkRequests() {
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

        return request.uri().toString();
    }

    public String getUsersBasicInfoFromVkApiBy(String ids, String fields){
        try {
            return this.vkApiMethodUsersGet(ids, fields);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    private String vkApiMethodUsersGet(String userIds, String fields) throws IOException, InterruptedException {
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