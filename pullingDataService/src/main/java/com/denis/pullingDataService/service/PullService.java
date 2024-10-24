package com.denis.pullingDataService.service;

import com.denis.pullingDataService.configuration.Config;
import com.denis.pullingDataService.dto.UserEntity;
import com.denis.pullingDataService.dto.vkUsersRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.*;

@Service
@RequiredArgsConstructor
public class PullService {
    @Autowired
    public final Config config;

    @Value("${global.URL_VK_SERVICE}")
    private String URL_VK_SERVICE;
    @Value("${global.URL_KAFKA_SERVICE}")
    private String URL_KAFKA_SERVICE;
    @Value("${global.numberOfThreads}")
    private int NUMBER_OF_THREADS;

    private final String[] FIELDS_OF_USER_TO_GET = UserEntity.getListOfStringFields();

    public void startPulling(int fromId, int toId) {
        try{
            this.splitAndStartMultiThreadDownloading(fromId, toId);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e.getCause());
        }
    }

    public void splitAndStartMultiThreadDownloading(int fromId, int toId) throws ExecutionException, InterruptedException {
        int numberOfUsers = toId - fromId;
        int step = this.NUMBER_OF_THREADS * 1000;

        if(numberOfUsers < step){
            throw new RuntimeException("Size of users should be more or equals " + step);
        }

        for(int i = 0; i < numberOfUsers / step; i++){
            int fromSplitId = fromId + i * step;
            int toSplitId = Math.min(fromSplitId + step, toId);
            this.startMultiThreadDownloading(fromSplitId, toSplitId);
        }
    }

    public void startMultiThreadDownloading(int fromId, int toId) throws InterruptedException, ExecutionException {
        int usersPerThread = (toId - fromId + 1) / this.NUMBER_OF_THREADS;
        List<Runnable> tasks = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(this.NUMBER_OF_THREADS);

        for(int i = 0; i < this.NUMBER_OF_THREADS; i++){
            int threadFromId = fromId + i * usersPerThread;
            int threadToId = (i == this.NUMBER_OF_THREADS - 1) ? toId : threadFromId + usersPerThread - 1;
            tasks.add(() -> {
                var response = this.pullUsersFromVkService(threadFromId, threadToId);
                this.config.restTemplate()
                        .postForEntity(this.URL_KAFKA_SERVICE + "/bdInsertMessageBroker", response, Void.class);

            });
        }

        for(var task: tasks){
            executor.submit(task);
        }

        executor.shutdown();
    }

    public String pullUsersFromVkService(int fromId, int toId) {
        int[] ids = this.getArrayOfIds(fromId, toId);
        String response = this.config.restTemplate()
                .postForEntity(this.URL_VK_SERVICE, new vkUsersRequest(ids, this.FIELDS_OF_USER_TO_GET), String.class)
                .getBody();

        return response;
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
