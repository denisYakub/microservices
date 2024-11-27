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
    private int life_main;
    private int people_main;
    private int political;
    private int smoking;

    @Column(columnDefinition = "TEXT")
    private String inspired_by;
    @Column(columnDefinition = "TEXT")
    private String religion;

    private List<String> langs;

    @OneToOne(mappedBy = "personal")
    private UserEntity user;

    @Override
    public String toString() {
        return "{" +
                "\"alcohol\":" + "\"" + alcohol + "\"" +
                ", \"inspired_by\":" + "\"" + inspired_by + "\"" +
                ", \"langs\":" + "[\"" + (langs != null ? String.join("\", \"", langs) : "null") + "\"]" +
                ", \"life_main\":" + "\"" + life_main + "\"" +
                ", \"people_main\":" + "\"" + people_main + "\"" +
                ", \"political\":" + "\"" + political + "\"" +
                ", \"religion\":" + "\"" + religion + "\"" +
                ", \"smoking\":" + "\"" + smoking + "\"" +
                '}';
    }
}
