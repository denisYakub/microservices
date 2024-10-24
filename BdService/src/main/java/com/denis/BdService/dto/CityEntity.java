package com.denis.BdService.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
