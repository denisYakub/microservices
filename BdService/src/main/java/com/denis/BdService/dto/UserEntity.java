package com.denis.BdService.dto;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "users")
@Data
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @Column(unique = true)
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

    @Column(columnDefinition = "TEXT")
    private String about;
    @Column(columnDefinition = "TEXT")
    private String activities;
    @Column(columnDefinition = "TEXT")
    private String books;
    @Column(columnDefinition = "TEXT")
    private String site;
    @Column(columnDefinition = "TEXT")
    private String movies;
    @Column(columnDefinition = "TEXT")
    private String music;
    @Column(columnDefinition = "TEXT")
    private String games;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    @Nullable
    private CityEntity city;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "occupation_id", referencedColumnName = "id")
    private OccupationEntity occupation;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CareerEntity> career;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<RelativeEntity> relatives;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personal_id", referencedColumnName = "id")
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

