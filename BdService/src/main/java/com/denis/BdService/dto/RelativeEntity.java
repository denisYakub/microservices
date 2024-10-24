package com.denis.BdService.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Entity
@Table(name = "relatives")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RelativeEntity {
    @Id
    @Column(unique = true)
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
