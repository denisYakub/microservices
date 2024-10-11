package com.denis.pullingDataService.service;

import com.denis.pullingDataService.dto.CityEntity;
import com.denis.pullingDataService.dto.UserEntity;
import com.denis.pullingDataService.dto.UsersResponse;
import com.denis.pullingDataService.repository.CityRepository;
import com.denis.pullingDataService.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor()
public class PostgresqlService {
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private UserRepository userRepository;

    @Value("${global.numberOfThreads}")
    private int NUMBER_OF_THREADS;

    public void saveUsersResponses(List<UsersResponse> usersResponses){
        try{
            this.splitAndStartMultiThreadSaving(usersResponses);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private void splitAndStartMultiThreadSaving(List<UsersResponse> usersResponses){
        for(int i = 0; i < usersResponses.size(); i += this.NUMBER_OF_THREADS) {
            this.startMultiThreadSaving(usersResponses.subList(i, i + this.NUMBER_OF_THREADS));
        }
    }

    private void startMultiThreadSaving(List<UsersResponse> usersResponses){
        ExecutorService executor = Executors.newFixedThreadPool(this.NUMBER_OF_THREADS);
        List<Runnable> tasks = new ArrayList<>();

        for (int i = 0; i < this.NUMBER_OF_THREADS; i++) {
            int finalI = i;
            tasks.add(() -> saveListOfUsers(usersResponses.get(finalI).getResponse()));
        }

        for (var task : tasks) {
            executor.submit(task);
        }

        executor.shutdown();
    }

    @Transactional
    private void saveListOfUsers(List<UserEntity> users) throws DataAccessException {
        //TODO оптимизировать запрос, чтоб не было лишних запросов в бд
        //HashMap<CityEntity, String> cityHash = new HashMap<>();

        for (UserEntity user : users) {
            if (!user.cityIsNull()) {
                CityEntity city = user.getCity();
                CityEntity existingCity = cityRepository.findByTitle(city.getTitle());
                if (existingCity != null) {
                    user.setCity(existingCity);
                } else {
                    cityRepository.save(city);
                }
            }
        }
        userRepository.saveAll(users);
    }
}
