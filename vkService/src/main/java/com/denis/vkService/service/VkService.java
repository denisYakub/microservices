package com.denis.vkService.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class VkService {
    private final String ACCESS_KEY = "656097086560970865609708b8667f2cf86656065609708039f2e0f8b8b1d17a8d64e55";
    private final int API_ID = 52411376;
    private final String CLIENT_SECRET = "4RCgqt0XSBYh1sD19uSd";
    private final String REDIRECT_URI = "http://localhost";

    private final HttpClient client = HttpClient.newHttpClient();

    public String getCode() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(
                        URI.create("https://oauth.vk.com/authorize" +
                                "?response_type=token" +
                                "&client_id=" + API_ID +
                                "&state=photos,groups" +
                                "&scope=offline" +
                                "display=page" +
                                "&v=5.199")
                ).GET().build();
        //Переходим по ссылке
        //Нажимаем войти как денис и из поисковой строки копируем ACCESS_TOKEN и заменяем его
        return request.uri().toString();
        //TODO заменить работу ручками на работу кодом
        //return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }

    public String getUserInfo(String userIds, String fields) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(
                        URI.create("https://api.vk.com/method/users.get" +
                                "?access_token=" + System.getenv("ACCESS_TOKEN") +
                                "&user_ids=" + userIds +
                                "&fields=" + fields +
                                "&v=5.199")
                ).GET().build();
        return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }



}
