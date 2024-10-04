package com.denis.vkService.service;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;

//@Service
public class VkService {

    private final String SECURES_KEY_VK = "BE35kuav9Vb7yXKEPjqU";
    private final String ACCESS_KEY_VK = "96eb9b9a96eb9b9a96eb9b" +
            "9acc95f4206a996eb96eb9b9af015f4d2e2dc0158ccb29177";

    public void getUserInfo(){
        try {
            UserAuthResponse authResponse = this.getAuthResponse();

            UserActor actor = new UserActor(
                    authResponse.getUserId().longValue(),
                    authResponse.getAccessToken()
            );

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private UserAuthResponse getAuthResponse() throws Exception {
        TransportClient transportClient = new HttpTransportClient();
        VkApiClient vk = new VkApiClient(transportClient);

        return vk.oAuth()
                .userAuthorizationCodeFlow(
                        Integer.getInteger(System.getenv("APP_ID")),
                        System.getenv("CLIENT_SECRET"),
                        System.getenv("REDIRECT_URI"),
                        System.getenv("ACCESS_KEY")
                ).execute();

    }
}
