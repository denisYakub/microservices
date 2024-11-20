package com.denis.BdService.services;

import com.denis.BdService.dto.*;
import com.denis.BdService.repository.*;
import com.denis.BdService.service.PostgresqlService;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PostgresServiceTests {
    @Mock
    private CityRepository cityRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PersonalRepository personalRepository;
    @Mock
    private OccupationRepository occupationRepository;
    @Mock
    private CareerRepository careerRepository;

    private final Gson gson = new Gson();

    @InjectMocks
    private PostgresqlService postgresql = new PostgresqlService(gson);

    @Test
    public void test_saveUsersRequest_method(){
        UserEntity user1 = new UserEntity();
        UserEntity user2 = new UserEntity();

        UsersRequest request = UsersRequest.builder().response(List.of(
                        new UserEntity[]{
                                this.setUpUserWithNonExistingEntities(user1),
                                this.setUpUserWithExistingEntities(user2)}
                ))
                .build();

        if(request.getCountOfUsers() != 2){
            fail("Должно быть 2 пользователя в request");
        }

        this.postgresql.saveUsersRequest(request.toString());
    }

    @Test
    public void test_deleteUsers_method(){
        FieldToDeleteBy<String> field1 = new FieldToDeleteBy<String>("nickname", "bob");
        FieldToDeleteBy<String> field2 = new FieldToDeleteBy<String>("bdate", "12.05.2001");
        FieldsToDeleteBy<String> fields = new FieldsToDeleteBy<>(field1, field2);

        this.postgresql.deleteUsers(fields);

        verify(userRepository, times(1)).deleteUsersByDynamicFields(any());
    }

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

        when(cityRepository.findByTitle("don't_exist")).thenReturn(null);
        when(cityRepository.findByTitle("exist"))
                .thenReturn(
                        CityEntity.builder()
                                .id(0)
                                .title("existing_test_city")
                                .build()
                );

        when(occupationRepository.findById(0)).thenReturn(Optional.empty());
        when(occupationRepository.findById(1))
                .thenReturn(
                        Optional.ofNullable(OccupationEntity.builder()
                                        .id(1)
                                        .type("test_type")
                                        .name("test_name")
                                .build())
                );

        when(cityRepository.save(any(CityEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(occupationRepository.save(any(OccupationEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(personalRepository.save(any(PersonalEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(userRepository.saveAll(any())).thenAnswer(invocation -> invocation.getArgument(0));

        doNothing().when(userRepository).deleteUsersByDynamicFields(any());
    }

    private UserEntity setUpUserWithNonExistingEntities(UserEntity user1){
        return UserEntity.builder()
                .id(0)
                .can_access_closed(true)
                .is_closed(false)
                .relation(0)
                .verified(0)
                .has_photo(0)
                .sex(0)
                .university(0)
                .faculty(0)
                .graduation(0)
                .first_name("test_first_name")
                .last_name("test_last_name")
                .nickname("test_nickname")
                .home_town("test_home_town")
                .maiden_name("test_maiden_name")
                .screen_name("test_screen_name")
                .bdate("0.0.0")
                .university_name("test_university_name")
                .faculty_name("test_faculty_name")
                .about("test_about")
                .activities("test_activities")
                .books("test_books")
                .site("test_site")
                .movies("test_movies")
                .music("test_music")
                .games("test_games")
                .city(
                        CityEntity.builder()
                                .id(1)
                                .title("don't_exist")
                                .build()
                )
                .occupation(
                        OccupationEntity.builder()
                                .id(0)
                                .name("test_occupation")
                                .type("test_occupation_type")
                                .build()
                )
                .career(List.of(new CareerEntity[]{
                        CareerEntity.builder()
                                .id(3)
                                .group_id(3)
                                .company("test_career_company")
                                .city_id(3)
                                .city_name("test_career_city_name")
                                .start(3)
                                .until(3)
                                .position("test_career_position")
                                .user(user1)
                                .build()
                }))
                .relatives(List.of(new RelativeEntity[]{
                        RelativeEntity.builder()
                                .id(4)
                                .name("test_relative_name")
                                .type("test_relative_type")
                                .user(user1)
                                .build()
                }))
                .personal(
                        PersonalEntity.builder()
                                .id(5)
                                .alcohol(5)
                                .inspired_by("test_personal_inspired_by")
                                .langs(List.of(new String[]{
                                        "test_personal_lang_1",
                                        "test_personal_lang_2"
                                }))
                                .life_main(5)
                                .people_main(5)
                                .political(5)
                                .religion("test_personal_religion")
                                .smoking(5)
                                .user(user1)
                                .build()
                )
                .build();
    }

    private UserEntity setUpUserWithExistingEntities(UserEntity user2){
        return UserEntity.builder()
                .id(1)
                .can_access_closed(true)
                .is_closed(false)
                .relation(0)
                .verified(0)
                .has_photo(0)
                .sex(0)
                .university(0)
                .faculty(0)
                .graduation(0)
                .first_name("test_first_name")
                .last_name("test_last_name")
                .nickname("test_nickname")
                .home_town("test_home_town")
                .maiden_name("test_maiden_name")
                .screen_name("test_screen_name")
                .bdate("0.0.0")
                .university_name("test_university_name")
                .faculty_name("test_faculty_name")
                .about("test_about")
                .activities("test_activities")
                .books("test_books")
                .site("test_site")
                .movies("test_movies")
                .music("test_music")
                .games("test_games")
                .city(
                        CityEntity.builder()
                                .id(2)
                                .title("exist")
                                .build()
                )
                .occupation(
                        OccupationEntity.builder()
                                .id(1)
                                .name("test_occupation")
                                .type("test_occupation_type")
                                .build()
                )
                .career(List.of(new CareerEntity[]{
                        CareerEntity.builder()
                                .id(3)
                                .group_id(3)
                                .company("test_career_company")
                                .city_id(3)
                                .city_name("test_career_city_name")
                                .start(3)
                                .until(3)
                                .position("test_career_position")
                                .user(user2)
                                .build()
                }))
                .relatives(List.of(new RelativeEntity[]{
                        RelativeEntity.builder()
                                .id(4)
                                .name("test_relative_name")
                                .type("test_relative_type")
                                .user(user2)
                                .build()
                }))
                .personal(
                        PersonalEntity.builder()
                                .id(5)
                                .alcohol(5)
                                .inspired_by("test_personal_inspired_by")
                                .langs(List.of(new String[]{
                                        "test_personal_lang_1",
                                        "test_personal_lang_2"
                                }))
                                .life_main(5)
                                .people_main(5)
                                .political(5)
                                .religion("test_personal_religion")
                                .smoking(5)
                                .user(user2)
                                .build()
                )
                .build();
    }
}
