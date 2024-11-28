package com.denis.pullingDataService.service;

import com.denis.pullingDataService.configuration.Config;
import com.denis.pullingDataService.dto.vkUsersRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.*;

@Service
@RequiredArgsConstructor
public class PullService {
    @Value("${application.URL_VK_SERVICE}")
    public String URL_VK_SERVICE;
    @Value("${application.URL_KAFKA_SERVICE}")
    public String URL_KAFKA_SERVICE;
    @Value("${application.NUMBER_OF_THREADS}")
    public int NUMBER_OF_THREADS;
    @Value("${application.CHUNK_SIZE}")
    public int CHUNK_SIZE;

    public final String[] FIELDS_OF_USER_TO_GET = new String[]{"about", "activities",
            "bdate", "books",
            "relation", "relatives",
            "verified",
            "screen_name", "sex", "site",
            "movies", "music",
            "nickname",
            "has_photo", "home_town",
            "maiden_name", "military",
            "games",
            "city", "career", "connections", "contacts", "counters", "country",
            "education",
            "occupation",
            "personal"
    };

    public void startPulling(int fromId, int toId) {
        this.startMultiThreadDownloading(fromId, toId);
    }

    public void startMultiThreadDownloading(int fromId, int toId) {
        ExecutorService executorService = Executors.newFixedThreadPool(this.NUMBER_OF_THREADS);
        for(int i = fromId; i <= toId; i += CHUNK_SIZE){
            int IdFrom = i;
            int idTo = Math.min(i + this.CHUNK_SIZE - 1, toId);
            executorService.submit(() -> {
                var response = this.pullUsersFromVkService(IdFrom, idTo);
                this.postRequest(
                        this.URL_KAFKA_SERVICE + "/bdInsertMessageBroker",
                            response,
                            Void.class
                        );
            });
        }

        executorService.shutdown();
    }

    public String pullUsersFromVkService(int fromId, int toId) {
        int[] ids = this.getArrayOfIds(fromId, toId);

        return this.postRequest(
                this.URL_VK_SERVICE,
                new vkUsersRequest(ids, this.FIELDS_OF_USER_TO_GET),
                String.class
        ).getBody().toString();
    }

    public int[] getArrayOfIds(int firstId, int lastId){
        int sizeOfIdsArray = lastId - firstId + 1;
        int[] ids = new int[sizeOfIdsArray];
        int valueOfIdsArray = firstId;

        for(int i = 0; i < sizeOfIdsArray; i++){
            ids[i] = valueOfIdsArray++;
        }

        return ids;
    }

    public <T>ResponseEntity postRequest(String url, Object body,  Class<T> responseType){
        return Config.restTemplate().postForEntity(
                url,
                body,
                responseType
        );
    }
}
