package com.denis.sortAndAnalyzeService.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OccupationEntity {
    private int id;

    private String name;
    private String type;

    @Override
    public String toString(){
        return "{" +
                "\"name\":" + "\"" + name + "\"" +
                ", \"type\":" + "\"" + type + "\"" +
                "}";
    }
}
