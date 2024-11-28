package com.denis.sortAndAnalyzeService.dto;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    private int id;

    private boolean can_access_closed;
    private boolean is_closed;


    private int relation;
    private int verified;
    private int has_photo;
    private int sex;
    private int university;
    private int faculty;
    private int graduation;

    private String first_name;
    private String last_name;
    private String nickname;
    private String home_town;
    private String maiden_name;
    private String screen_name;
    private String bdate;
    private String university_name;
    private String faculty_name;

    private String about;
    private String activities;
    private String books;
    private String site;
    private String movies;
    private String music;
    private String games;

    private CityEntity city;

    private OccupationEntity occupation;

    private List<CareerEntity> career;

    private List<RelativeEntity> relatives;

    private PersonalEntity personal;

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + "\"" + id + "\"" +
                ", \"nickname\":" + "\"" + nickname + "\"" +
                ", \"bdate\":" + "\"" + bdate + "\"" +
                ", \"city\":" + city +
                ", \"has_photo\":" + "\"" + has_photo + "\"" +
                ", \"books\":" + "\"" + books + "\"" +
                ", \"about\":" + "\"" + about + "\"" +
                ", \"games\":" + "\"" + games + "\"" +
                ", \"movies\":" + "\"" + movies + "\"" +
                ", \"activities\":" + "\"" + activities + "\"" +
                ", \"music\":" + "\"" + music + "\"" +
                ", \"site\":" + "\"" + site + "\"" +
                ", \"occupation\":" + occupation +
                ", \"career\":" + career +
                ", \"university\":" + "\"" + university + "\"" +
                ", \"university_name\":" + "\"" + university_name + "\"" +
                ", \"faculty\":" + "\"" + faculty + "\"" +
                ", \"faculty_name\":" + "\"" + faculty_name + "\"" +
                ", \"graduation\":" + "\"" + graduation + "\"" +
                ", \"home_town\":" + "\"" + home_town + "\"" +
                ", \"relation\":" + "\"" + relation + "\"" +
                ", \"personal\":"+ personal +
                ", \"relatives\":" + relatives +
                ", \"sex\":" + "\"" + sex + "\"" +
                ", \"screen_name\":" + "\"" + screen_name + "\"" +
                ", \"verified\":" + "\"" + verified + "\"" +
                ", \"first_name\":" + "\"" + first_name + "\"" +
                ", \"last_name\":" + "\"" + last_name + "\"" +
                ", \"can_access_closed\":" + "\"" + can_access_closed + "\"" +
                ", \"is_closed\":" + "\"" + is_closed + "\"" +
                '}';
    }

    public boolean cityIsNull(){
        return this.city == null;
    }

    public boolean occupationIsNull(){
        return this.occupation == null;
    }

    public boolean personalIsNull(){
        return this.personal == null;
    }

    public boolean careerIsNull(){
        return this.career == null;
    }

    public boolean relativeIsNull(){
        return this.relatives == null;
    }

    public int GetAge(){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.M.yyyy");

            return (2024 - LocalDate.parse(this.bdate, formatter).getYear());
        } catch (Exception e) {
            return -1;
        }
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

