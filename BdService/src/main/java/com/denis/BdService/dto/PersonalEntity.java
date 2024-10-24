package com.denis.BdService.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.ArrayList;

@Data
@Entity
@Table(name = "personals")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonalEntity {
    @Id
    public int id;

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
