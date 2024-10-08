package com.denis.pullingDataService.service;

import com.denis.pullingDataService.dto.UserEntity;
import com.denis.pullingDataService.repository.CityRepository;
import com.denis.pullingDataService.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PostgreSqlService {
    @Autowired
    private final CityRepository cityRepository;
    @Autowired
    private final UserRepository userRepository;

    public Optional<UserEntity> getUser(int id){
        return userRepository.getUserById(id);
    }
}
