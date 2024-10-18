package com.denis.BdService.service;

import com.denis.BdService.dto.UsersResponse;
import com.denis.BdService.repository.CityRepository;
import com.denis.BdService.repository.UserRepository;
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

    public void saveUsersResponses(List<UsersResponse> usersResponses){
        try{
            //this.splitAndStartMultiThreadSaving(usersResponses);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }


}
