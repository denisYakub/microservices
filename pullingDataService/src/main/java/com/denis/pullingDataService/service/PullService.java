package com.denis.pullingDataService.service;

import com.denis.pullingDataService.configuration.Config;
import com.denis.pullingDataService.dto.UserEntity;
import com.denis.pullingDataService.dto.vkUsersRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

@Service
@RequiredArgsConstructor
public class PullService {
    @Autowired
    public final Config config;

    @Value("${application.URL_VK_SERVICE}")
    private String URL_VK_SERVICE;
    @Value("${application.URL_KAFKA_SERVICE}")
    private String URL_KAFKA_SERVICE;
    @Value("${application.NUMBER_OF_THREADS}")
    private int NUMBER_OF_THREADS;
    @Value("${application.CHUNK_SIZE}")
    private int CHUNK_SIZE;

    private final String[] FIELDS_OF_USER_TO_GET = UserEntity.getListOfStringFields();

    public void startPulling(int fromId, int toId) {
        System.out.println(this.URL_VK_SERVICE);
        this.startMultiThreadDownloading(fromId, toId);
    }

    public void startMultiThreadDownloading(int fromId, int toId) {
        ExecutorService executorService = Executors.newFixedThreadPool(this.NUMBER_OF_THREADS);
        for(int i = fromId; i <= toId; i += CHUNK_SIZE){
            int IdFrom = i;
            int idTo = Math.min(i + this.CHUNK_SIZE - 1, toId);
            executorService.submit(() -> {
                var response = this.pullUsersFromVkService(IdFrom, idTo);
                Config.restTemplate()
                        .postForEntity(this.URL_KAFKA_SERVICE + "/bdInsertMessageBroker", response, Void.class);
            });
        }

        executorService.shutdown();
    }

    public String pullUsersFromVkService(int fromId, int toId) {
        int[] ids = this.getArrayOfIds(fromId, toId);

        return Config.restTemplate()
                .postForEntity(this.URL_VK_SERVICE, new vkUsersRequest(ids, this.FIELDS_OF_USER_TO_GET), String.class)
                .getBody();
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
}
