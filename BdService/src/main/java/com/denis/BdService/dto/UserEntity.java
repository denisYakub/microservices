package com.denis.BdService.dto;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
    @Id
    @Column(unique = true)
    private int id;

    private String first_name;
    private String last_name;
    private boolean can_access_closed;
    private boolean is_closed;


    private int relation;
    private int verified;
    private int has_photo;
    private int sex;

    private String nickname;
    private String home_town;
    private String maiden_name;
    private String screen_name;
    private String bdate;

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

    /*@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    @Nullable
    private CountryEntity country;

    private EducationEntity education;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    @Nullable
    private ContactEntity contacts;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "counter_id", referencedColumnName = "id")
    @Nullable
    private CounterEntity counters;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "personal_id", referencedColumnName = "id")
    @Nullable
    private PersonalEntity personal;*/

    /*@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "career_id", referencedColumnName = "id")
    private Set<CareerEntity> career;*/

    /*@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "relatives_id", referencedColumnName = "id")
    private ArrayList<RelativeEntity> relatives;*/

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
                //", \"country\":" + "\"" + country + "\"" +
                //", \"career\":" + "\"" + career + "\"" +
                //", \"contacts\":" + "\"" + contacts + "\"" +
                //", \"counters\":" + "\"" + counters + "\"" +
                //", \"education\":" + "\"" + education + "\"" +
                //", \"personal\":" + "\"" + personal + "\"" +
                //", \"relatives\":" + "\"" + relatives + "\"" +
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

