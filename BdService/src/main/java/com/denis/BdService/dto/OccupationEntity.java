package com.denis.BdService.dto;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "occupations")
@Data
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OccupationEntity {
    @Id
    @Column(unique = true)
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
