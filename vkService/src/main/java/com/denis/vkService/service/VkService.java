package com.denis.vkService.service;

import com.denis.vkService.dto.UsersRecord;
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
    private final String ACCESS_TOKEN = null;

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

    public String getUsersInfoJSON(String userIds, String fields) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(
                            URI.create("https://api.vk.com/method/users.get" +
                                    "?access_token=" + System.getenv("ACCESS_TOKEN") +
                                    "&user_ids=" + userIds +
                                    "&fields=" + fields +
                                    "&v=5.199")
                    ).GET().build();

            var response = this.client.send(request, HttpResponse.BodyHandlers.ofString()).body();

            if(response.contains("error_msg")){
                throw new RuntimeException(response);
            }

            return response;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public UsersRecord getUsersInfoRecord(String userIds, String fields) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(
                            URI.create("https://api.vk.com/method/users.get" +
                                    "?access_token=" + System.getenv("ACCESS_TOKEN") +
                                    "&user_ids=" + userIds +
                                    "&fields=" + fields +
                                    "&v=5.199")
                    ).GET().build();
            String response = this.client.send(request, HttpResponse.BodyHandlers.ofString()).body();

            //TODO сделать возможным получение записи информации о пользователях в UsersRecord
            return this.gson.fromJson(response, UsersRecord.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
