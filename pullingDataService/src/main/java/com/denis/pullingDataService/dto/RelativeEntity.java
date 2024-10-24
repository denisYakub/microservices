package com.denis.pullingDataService.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RelativeEntity {
    public int id;

    private String name;
    private String type;

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + "\"" + id + "\"" +
                ", \"name\":" + "\"" + name + "\"" +
                ", \"type\":" + "\"" + type + "\"" +
                '}';
    }
}
