package com.denis.sortAndAnalyzeService.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CareerEntity {
    private int id;

    private int group_id;
    private String company;

    private int city_id;
    private String city_name;

    private int start;
    private int until;

    private String position;

    private UserEntity user;

    @Override
    public String toString() {
        return "{" +
                "\"group_id\":" + "\"" + group_id + "\"" +
                ", \"company\":" + "\"" + company + "\"" +
                ", \"city_id\":" + "\"" + city_id + "\"" +
                ", \"city_name\":" + "\"" + city_name + "\"" +
                ", \"from\":" + "\"" + start + "\"" +
                ", \"until\":" + "\"" + until + "\"" +
                ", \"position\":" + "\"" + position + "\"" +
                '}';
    }
}
