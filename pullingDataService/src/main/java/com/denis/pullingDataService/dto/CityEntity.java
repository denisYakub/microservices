package com.denis.pullingDataService.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "cities")
@Getter
@Setter
public class CityEntity{
    @Id
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
