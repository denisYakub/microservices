package com.denis.pullingDataService.service;

import com.denis.pullingDataService.configuration.Config;
import com.denis.pullingDataService.dto.UsersRequest;
import com.denis.pullingDataService.dto.UsersResponse;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Time;
import java.util.*;
import java.util.concurrent.*;

@Service
@RequiredArgsConstructor
public class PullService {
    @Autowired
    public final PostgresqlService postgresqlService;

    public final RestTemplate restTemplate = new RestTemplate();
    public final Gson gson = new Gson();

    @Value("${global.numberOfThreads}")
    private int NUMBER_OF_THREADS;

    private final String URL_VK_SERVICE = "http://localhost:80/api/vk";
    private final String[] FIELDS_OF_USER_TO_GET = new String[]{"city"};


    public void startPulling(int fromId, int toId) {
        try{
            List<UsersResponse> results = this.splitAndStartMultiThreadDownloading(fromId, toId);
            this.postgresqlService.saveUsersResponses(results);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e.getCause());
        }
    }

    public List<UsersResponse> splitAndStartMultiThreadDownloading(int fromId, int toId) throws ExecutionException, InterruptedException {
        List<UsersResponse> results = new ArrayList<>();
        int numberOfUsers = toId - fromId;
        int step = this.NUMBER_OF_THREADS * 1000;

        if(numberOfUsers < step){
            throw new RuntimeException("Size of users should be more or equals " + step);
        }

        for(int i = 0; i < numberOfUsers / step; i++){
            int fromSplitId = fromId + i * step;
            int toSplitId = Math.min(fromSplitId + step, toId);
            results.addAll(this.startMultiThreadDownloading(fromSplitId, toSplitId));
        }

        return results;
    }

    public List<UsersResponse> startMultiThreadDownloading(int fromId, int toId) throws InterruptedException, ExecutionException {
        int usersPerThread = (toId - fromId + 1) / this.NUMBER_OF_THREADS;
        List<Callable<UsersResponse>> tasks = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(this.NUMBER_OF_THREADS);

        for(int i = 0; i < this.NUMBER_OF_THREADS; i++){
            int threadFromId = fromId + i * usersPerThread;
            int threadToId = (i == this.NUMBER_OF_THREADS - 1) ? toId : threadFromId + usersPerThread - 1;
            tasks.add(() -> this.pullUsersFromVkService(threadFromId, threadToId));
        }

        var responses = executor.invokeAll(tasks);
        List<UsersResponse> results = new ArrayList<>();

        for(var response: responses){
            results.add(response.get());
        }

        executor.shutdown();

        return results;
    }

    public UsersResponse pullUsersFromVkService(int fromId, int toId) {
        int[] ids = this.getArrayOfIds(fromId, toId);
        String response = this.restTemplate
                .postForEntity(this.URL_VK_SERVICE, new UsersRequest(ids, this.FIELDS_OF_USER_TO_GET), String.class)
                .getBody();

        return this.gson.fromJson(response, UsersResponse.class);
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
