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
@RequiredArgsConstructor
public class PostgresqlService {
    @Autowired
    public final Gson gson;
    @Autowired
    public CityRepository cityRepository;
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public PersonalRepository personalRepository;
    @Autowired
    public OccupationRepository occupationRepository;
    @Autowired
    public CareerRepository careerRepository;

    @Value("${application.numberOfThreads}")
    public int NUMBER_OF_THREADS;

    public void saveUsersRequest(String usersRequestJson){
        try{
            UsersRequest usersRequest = gson.fromJson(usersRequestJson, UsersRequest.class);
            this.saveListOfUsers(usersRequest.getResponse());
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void saveListOfUsers(List<UserEntity> users) throws DataAccessException {
        //TODO оптимизировать
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

            if(!user.occupationIsNull()){
                OccupationEntity occupation = user.getOccupation();
                var existingOccupation = this.occupationRepository.findById(occupation.getId());

                if(existingOccupation.isPresent()){
                    user.setOccupation(existingOccupation.get());
                }else {
                    occupationRepository.save(occupation);
                }
            }

            if(!user.careerIsNull()){
                for(var career: user.getCareer())
                    career.setUser(user);
            }

            if(!user.relativeIsNull()){
                for (var relative: user.getRelatives())
                    relative.setUser(user);
            }

            if(!user.personalIsNull())
                this.personalRepository.save(user.getPersonal());
        }
        userRepository.saveAll(users);
    }

    @Transactional
    public void deleteUsers(FieldsToDeleteBy fields){
        //this.careerRepository.;
        this.userRepository.deleteUsersByDynamicFields(fields.getListOfFields());
    }
}
