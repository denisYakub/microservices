package com.denis.pullingDataService.service;

import com.denis.pullingDataService.dto.CityEntity;
import com.denis.pullingDataService.dto.UserEntity;
import com.denis.pullingDataService.dto.UsersResponse;
import com.denis.pullingDataService.repository.CityRepository;
import com.denis.pullingDataService.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@AllArgsConstructor
public class PostgresqlService {
    private final int NUMBER_OF_THREADS = 8;

    @Autowired
    private final CityRepository cityRepository;
    @Autowired
    private final UserRepository userRepository;

    @Transactional
    public void saveListOfUsers(List<UsersResponse> responses){
        try {
            //TODO интегрировать алгоритм из метода saveUser() для данного метода с многопоточностью
        }catch (DataAccessException e){
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void saveUser(){
        try {
            UserEntity user1 = UserEntity.builder()
                    .id(1)
                    .first_name("Denis")
                    .last_name("Yakubov")
                    .is_closed(true)
                    .can_access_closed(true)
                    .city(
                            CityEntity.builder()
                                    .id(2)
                                    .title("SPB")
                                    .build()
                    )
                    .build();
            UserEntity user2 = UserEntity.builder()
                    .id(2)
                    .first_name("Vlad")
                    .last_name("Yakubov")
                    .is_closed(true)
                    .can_access_closed(false)
                    .city(
                            CityEntity.builder()
                                    .id(2)
                                    .title("SPB")
                                    .build()
                    )
                    .build();
            UserEntity user3 = UserEntity.builder()
                    .id(3)
                    .first_name("Ruslan")
                    .last_name("Zavod")
                    .is_closed(false)
                    .can_access_closed(false)
                    .city(
                            CityEntity.builder()
                                    .id(2)
                                    .title("SPB")
                                    .build()
                    )
                    .build();

            UserEntity user4 = UserEntity.builder()
                    .id(4)
                    .first_name("Anna")
                    .last_name("Thar")
                    .is_closed(false)
                    .can_access_closed(false)
                    .city(null)
                    .build();

            List<UserEntity> users = new ArrayList<>();
            users.add(user1);
            users.add(user2);
            users.add(user3);
            users.add(user4);

            //TODO оптимизировать запрос, чтоб не было лишних запросов в бд
            //HashMap<CityEntity, String> cityHash = new HashMap<>();

            for (UserEntity user : users) {
                CityEntity city = user.getCity();
                if (city != null) {
                    CityEntity existingCity = cityRepository.findByTitle(city.getTitle());
                    if (existingCity != null) {
                        // Используем существующий город
                        user.setCity(existingCity);
                    } else {
                        // Сохраняем новый город каскадно
                        cityRepository.save(city);
                    }
                }
            }

            userRepository.saveAll(users);
        } catch (DataAccessException e) {
            System.out.println(e);
        }

    }
}
