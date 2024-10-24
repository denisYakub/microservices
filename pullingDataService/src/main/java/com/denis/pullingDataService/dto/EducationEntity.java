package com.denis.pullingDataService.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EducationEntity {
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
