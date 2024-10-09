package com.denis.pullingDataService.dto;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "cities")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityEntity{
    @Id
    @Column(unique = true)
    public int id;
    @Column(unique = true)
    public String title;

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + "\"" + id + "\"" +
                ", \"title\":" + "\"" + title + "\"" +
                '}';
    }

}
