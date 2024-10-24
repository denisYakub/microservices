package com.denis.pullingDataService.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityEntity{
    public int id;

    public String title;

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + "\"" + id + "\"" +
                ", \"title\":" + "\"" + title + "\"" +
                '}';
    }

}
