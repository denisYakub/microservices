package com.denis.sortAndAnalyzeService.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RelativeEntity {
    private int id;

    private String name;
    private String type;

    private UserEntity user;

    @Override
    public String toString() {
        return "{" +
                "\"id_relative\":" + "\"" + id + "\"" +
                ", \"name\":" + "\"" + name + "\"" +
                ", \"type\":" + "\"" + type + "\"" +
                '}';
    }
}
