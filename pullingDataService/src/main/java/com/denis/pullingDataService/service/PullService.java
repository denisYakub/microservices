package com.denis.pullingDataService.service;

import com.denis.pullingDataService.configuration.Config;
import com.denis.pullingDataService.dto.UsersRequest;
import com.denis.pullingDataService.dto.UsersResponse;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.sql.Time;
import java.util.*;
import java.util.concurrent.*;

@Service
@AllArgsConstructor
public class PullService {
    private final String URL_VK_SERVICE = "http://localhost:80/api/vk";
    private final String[] FIELDS_OF_USER_TO_GET = new String[]{"city"};
    private final int NUMBER_OF_THREADS = 8;

    private final Config config;
    private final Gson gson;

    public List<UsersResponse> startDownloadingUsersFromIdToId(int fromId, int toId) {
        try{
            return this.startMultiThreadDownloading(fromId, toId);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("ERROR IN RUNNING .startMultiThreadDownloading()");
        }
    }

    public List<UsersResponse> startDownloadingUsers(int numberOfUsers) {
        try {
            List<UsersResponse> results = new ArrayList<>();
            int step = this.NUMBER_OF_THREADS * 1000;

            for(int i = 0; i < numberOfUsers / step; i++){
                int fromId = i * step + 1;
                int toId = (i + 1) * step;
                results.addAll(this.startMultiThreadDownloading(fromId, toId));
            }

            return results;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("ERROR IN RUNNING .startMultiThreadDownloading()");
        }
    }

    private List<UsersResponse> startMultiThreadDownloading(int fromId, int toId) throws InterruptedException, ExecutionException {
        int usersPerThread = (toId - fromId + 1) / this.NUMBER_OF_THREADS;
        List<Callable<UsersResponse>> tasks = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(this.NUMBER_OF_THREADS);

        for(int i = 0; i < this.NUMBER_OF_THREADS; i++){
            int threadFromId = fromId + i * usersPerThread;
            int threadToId = (i == this.NUMBER_OF_THREADS - 1) ? toId : threadFromId + usersPerThread - 1;
            tasks.add(() -> this.pullUsersFromVkServiceByIds(threadFromId, threadToId));
        }

        var responses = executor.invokeAll(tasks);
        List<UsersResponse> results = new ArrayList<>();

        for(var response: responses){
            results.add(response.get());
        }

        executor.shutdown();

        return results;
    }

    private UsersResponse pullUsersFromVkServiceByIds(int fromId, int toId){
        int[] ids = this.getArrayOfIds(fromId, toId);
        String response = this.config.restTemplate()
                .postForEntity(this.URL_VK_SERVICE, new UsersRequest(ids, this.FIELDS_OF_USER_TO_GET), String.class)
                .getBody();

        return this.gson.fromJson(response, UsersResponse.class);
    }

    private int[] getArrayOfIds(int firstId, int lastId){
        int sizeOfIdsArray = lastId - firstId + 1;
        int[] ids = new int[sizeOfIdsArray];
        int valueOfIdsArray = firstId;

        for(int i = 0; i < sizeOfIdsArray; i++){
            ids[i] = valueOfIdsArray++;
        }

        return ids;
    }

    private int countTotalDownloadedUsers(List<UsersResponse> results) {
        int total = 0;

        for (var result: results){
            total += result.getCountOfUsers();
        }

        return total;
    }
}
