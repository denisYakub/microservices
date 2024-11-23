package com.denis.BdService.dto;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class UserEntityTests {
    private UserEntity user;

    @Test
    public void test_UserEntity_class_methods(){
        if(user.careerIsNull()){
            fail("CareerEntity не должна быть null");
        }
        if(user.occupationIsNull()){
            fail("OccupationEntity не должна быть null");
        }
        if(user.personalIsNull()){
            fail("PersonalEntity не должна быть null");
        }
        if(user.cityIsNull()){
            fail("CityEntity не должна быть null");
        }
        if(user.relativeIsNull()){
            fail("RelativesEntity не должна быть null");
        }
        if(UserEntity.getListOfStringFields().length != 27){
            fail("Должно быть ровно 27 полей для запросов к vkApi");
        }
    }

    @Test
    public void test_UserEntity_class_for_JSON_stringify(){
        try{
          new JSONObject(user.toString());
        } catch (JSONException e) {
            fail("UserEntity должен конвертироваться в json: " + e.getMessage());
        }
    }

    @BeforeEach
    public void setUpUser(){
        this.user = UserEntity.builder()
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
                                .title("test_city")
                                .build()
                )
                .occupation(
                        OccupationEntity.builder()
                                .id(2)
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
                                .user(user)
                                .build()
                }))
                .relatives(List.of(new RelativeEntity[]{
                        RelativeEntity.builder()
                                .id(4)
                                .name("test_relative_name")
                                .type("test_relative_type")
                                .user(user)
                                .build()
                }))
                .personal(
                        PersonalEntity.builder()
                                .id(5)
                                .alcohol(5)
                                .inspired_by("test_personal_inspired_by")
                                .langs(List.of(new String[]{
                                        "test_personal_lang"
                                }))
                                .life_main(5)
                                .people_main(5)
                                .political(5)
                                .religion("test_personal_religion")
                                .smoking(5)
                                .user(user)
                                .build()
                )
                .build();
    }
}
