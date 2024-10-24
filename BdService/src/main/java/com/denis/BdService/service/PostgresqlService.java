package com.denis.BdService.service;

import com.denis.BdService.dto.*;
import com.denis.BdService.repository.*;
import com.google.gson.Gson;
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
    private final Gson gson;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private EducationRepository educationRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private CountersRepository countersRepository;
    @Autowired
    private PersonalRepository personalRepository;

    @Value("${global.numberOfThreads}")
    private int NUMBER_OF_THREADS;

    public void saveUsersRequest(String usersRequestJson){
        try{
            UsersRequest usersRequest = gson.fromJson(usersRequestJson, UsersRequest.class);
            this.saveListOfUsers(usersRequest.getResponse());
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    private void saveListOfUsers(List<UserEntity> users) throws DataAccessException {
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
