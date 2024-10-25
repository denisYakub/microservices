package com.denis.BdService.dto;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
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

    @OneToMany(mappedBy = "personal", cascade = CascadeType.ALL)
    private List<LanguageEntity> langs_full;

    private int life_main;
    private int people_main;

    private int political;
    private String religion;
    private int smoking;

    @OneToOne(mappedBy = "personal")
    private UserEntity user;

    public boolean isLangsFullNull(){
        return this.langs_full == null;
    }

    @Override
    public String toString() {
        return "{" +
                ", \"alcohol\":" + "\"" + alcohol + "\"" +
                ", \"inspired_by\":" + "\"" + inspired_by + "\"" +
                ", \"langs\":" + "\"" + langs.toString() + "\"" +
                ", \"langs_full\":" + "\"" + langs_full.toString() + "\"" +
                ", \"life_main\":" + "\"" + life_main + "\"" +
                ", \"people_main\":" + "\"" + people_main + "\"" +
                ", \"political\":" + "\"" + political + "\"" +
                ", \"religion\":" + "\"" + religion + "\"" +
                ", \"smoking\":" + "\"" + smoking + "\"" +
                '}';
    }
}
