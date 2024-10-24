package com.denis.BdService.dto;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "educations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EducationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true)
    private int id;

    private int university;

    private int faculty;

    private String university_name;
    private String faculty_name;
    private String graduation;

    @Override
    public String toString() {
        return "{" +
                "\"university\":" + "\"" + university + "\"" +
                ", \"faculty\":" + "\"" + faculty + "\"" +
                ", \"university_name\":" + "\"" + university_name + "\"" +
                ", \"faculty_name\":" + "\"" + faculty_name + "\"" +
                ", \"graduation\":" + "\"" + graduation + "\"" +
                '}';
    }
}
