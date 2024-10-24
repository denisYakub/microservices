package com.denis.pullingDataService.dto;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonalEntity {

    private int political;
    private ArrayList<String> langs;
    private String religion;
    private String inspired_by;
    private int people_main;
    private int life_main;
    private int smoking;
    private int alcohol;

    @Override
    public String toString() {
        return "{" +
                ", \"political\":" + "\"" + political + "\"" +
                ", \"religion\":" + "\"" + religion + "\"" +
                ", \"inspired_by\":" + "\"" + inspired_by + "\"" +
                ", \"people_main\":" + "\"" + people_main + "\"" +
                ", \"life_main\":" + "\"" + life_main + "\"" +
                ", \"smoking\":" + "\"" + smoking + "\"" +
                ", \"alcohol\":" + "\"" + alcohol + "\"" +
                ", \"langs\":" + "\"" + langs.toString() + "\"" +
                '}';
    }
}
