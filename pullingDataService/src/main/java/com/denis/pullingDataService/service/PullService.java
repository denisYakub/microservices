package com.denis.pullingDataService.service;

import com.denis.pullingDataService.configuration.Config;
import com.denis.pullingDataService.dto.UsersRequest;
import com.denis.pullingDataService.dto.UsersResponse;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
@AllArgsConstructor
public class PullService {
    private final String URL_VK_SERVICE = "http://localhost:80/api/vk";
    private final String[] FIELDS_OF_USER_TO_GET = new String[]{"city"};

    private final Config config;
    private final Gson gson;

    public String startDownloadingUsers(int numberOfUsers) throws InterruptedException, ExecutionException {
        int numberOfThreads = 4;
        int usersPerThread = numberOfUsers / numberOfThreads;
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

        List<Callable<UsersResponse>> tasks = new ArrayList<>();

        for(int i = 0; i < numberOfThreads; i++){
            int fromId = i * usersPerThread + 1;
            int toId = (i + 1) * usersPerThread;
            tasks.add(() -> this.pullUsersFromVkServiceByIds(fromId, toId));
        }

        List<Future<UsersResponse>> results = executor.invokeAll(tasks);

        StringBuilder response = new StringBuilder("\"vk_users\"=[");

        for(var result: results){
            int lastIndex  = result.get().toString().length() - 1;
            response.append(result.get().toString().substring(1, lastIndex)).append(", ");
        }

        executor.shutdown();

        return response.append("]").toString();
    }

    public UsersResponse pullUsersFromVkServiceByIds(int fromId, int toId){
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
}
