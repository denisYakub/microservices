package com.denis.pullingDataService.dto;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
    private int id;

    private String first_name;
    private String last_name;
    private boolean can_access_closed;
    private boolean is_closed;


    private int relation;
    private int verified;
    private int has_photo;
    private int sex;

    private String about;
    private String activities;
    private String bdate;
    private String books;
    private String screen_name;
    private String site;
    private String movies;
    private String music;
    private String nickname;
    private String home_town;
    private String maiden_name;
    private String games;

    private CityEntity city;
    private CountryEntity country;
    private EducationEntity education;
    private ContactEntity contacts;
    private CounterEntity counters;
    private PersonalEntity personal;

    private ArrayList<CareerEntity> career;
    private ArrayList<RelativeEntity> relatives;

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + "\"" + id + "\"" +
                ", \"first_name\":" + "\"" + first_name + "\"" +
                ", \"last_name\":" + "\"" + last_name + "\"" +
                ", \"can_access_closed\":" + "\"" + can_access_closed + "\"" +
                ", \"is_closed\":" + "\"" + is_closed + "\"" +
                ", \"about\":" + "\"" + about + "\"" +
                ", \"activities\":" + "\"" + activities + "\"" +
                ", \"bdate\":" + "\"" + bdate + "\"" +
                ", \"books\":" + "\"" + books + "\"" +
                ", \"relation\":" + "\"" + relation + "\"" +
                ", \"verified\":" + "\"" + verified + "\"" +
                ", \"screen_name\":" + "\"" + screen_name + "\"" +
                ", \"sex\":" + "\"" + sex + "\"" +
                ", \"site\":" + "\"" + site + "\"" +
                ", \"movies\":" + "\"" + movies + "\"" +
                ", \"music\":" + "\"" + music + "\"" +
                ", \"nickname\":" + "\"" + nickname + "\"" +
                ", \"has_photo\":" + "\"" + has_photo + "\"" +
                ", \"home_town\":" + "\"" + home_town + "\"" +
                ", \"maiden_name\":" + "\"" + maiden_name + "\"" +
                ", \"games\":" + "\"" + games + "\"" +
                ", \"city\":" + "\"" + city + "\"" +
                ", \"career\":" + "\"" + career + "\"" +
                ", \"contacts\":" + "\"" + contacts + "\"" +
                ", \"counters\":" + "\"" + counters + "\"" +
                ", \"country\":" + "\"" + country + "\"" +
                ", \"education\":" + "\"" + education + "\"" +
                ", \"personal\":" + "\"" + personal + "\"" +
                ", \"relatives\":" + "\"" + relatives + "\"" +
                '}';
    }

    public boolean cityIsNull(){
        return this.city == null;
    }

    public static String[] getListOfStringFields(){
        return new String[]{"about", "activities",
                "bdate", "books",
                "relation", "relatives",
                "verified",
                "screen_name", "sex", "site",
                "movies", "music",
                "nickname",
                "has_photo", "home_town",
                "maiden_name", "military",
                "games",
                "city", "career", "connections", "contacts", "counters", "country",
                "education",
                "occupation",
                "personal"
        };
    }

}
