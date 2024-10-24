package com.denis.pullingDataService.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CareerEntity {
    private int group_id;
    private String company;

    private int city_id;
    private String city_name;

    private int from;
    private int until;

    private String position;

    @Override
    public String toString() {
        return "{" +
                "\"group_id\":" + "\"" + group_id + "\"" +
                ", \"company\":" + "\"" + company + "\"" +
                ", \"city_id\":" + "\"" + city_id + "\"" +
                ", \"city_name\":" + "\"" + city_name + "\"" +
                ", \"from\":" + "\"" + from + "\"" +
                ", \"until\":" + "\"" + until + "\"" +
                ", \"position\":" + "\"" + position + "\"" +
                '}';
    }
}
