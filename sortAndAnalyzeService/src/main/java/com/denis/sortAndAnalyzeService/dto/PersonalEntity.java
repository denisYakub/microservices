package com.denis.sortAndAnalyzeService.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonalEntity {
    private int id;

    private int alcohol;
    private String inspired_by;

    private List<String> langs;

    private int life_main;
    private int people_main;

    private int political;
    private String religion;
    private int smoking;

    private UserEntity user;

    @Override
    public String toString() {
        return "{" +
                "\"alcohol\":" + "\"" + alcohol + "\"" +
                ", \"inspired_by\":" + "\"" + inspired_by + "\"" +
                ", \"langs\":" + "[\"" + String.join("\", \"", langs) + "\"]" +
                ", \"life_main\":" + "\"" + life_main + "\"" +
                ", \"people_main\":" + "\"" + people_main + "\"" +
                ", \"political\":" + "\"" + political + "\"" +
                ", \"religion\":" + "\"" + religion + "\"" +
                ", \"smoking\":" + "\"" + smoking + "\"" +
                '}';
    }
}
