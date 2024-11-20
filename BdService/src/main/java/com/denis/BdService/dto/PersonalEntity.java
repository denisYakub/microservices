package com.denis.BdService.dto;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private int alcohol;
    private String inspired_by;

    private List<String> langs;

    private int life_main;
    private int people_main;

    private int political;
    private String religion;
    private int smoking;

    @OneToOne(mappedBy = "personal")
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
