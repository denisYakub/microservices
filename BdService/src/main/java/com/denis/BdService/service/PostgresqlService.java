package com.denis.BdService.service;

import com.denis.BdService.dto.CityEntity;
import com.denis.BdService.dto.UserEntity;
import com.denis.BdService.dto.UsersRequest;
import com.denis.BdService.repository.CityRepository;
import com.denis.BdService.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor()
public class PostgresqlService {
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private UserRepository userRepository;

    @Value("${global.numberOfThreads}")
    private int NUMBER_OF_THREADS;

    public void saveUsersRequest(UsersRequest usersRequest){
        try{
            this.saveListOfUsers(usersRequest.response());
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAllRecordsFromBd(){

    }

    @Transactional
    private void saveListOfUsers(List<UserEntity> users) throws DataAccessException {
        //TODO оптимизировать запрос, чтоб не было лишних запросов в бд
        //HashMap<CityEntity, String> cityHash = new HashMap<>();

        for (UserEntity user : users) {
            if (!user.cityIsNull()) {
                CityEntity city = user.getCity();
                CityEntity existingCity = this.cityRepository.findByTitle(city.getTitle());
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
