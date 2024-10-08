package com.denis.pullingDataService.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private CityEntity city;
    private String first_name;
    private String last_name;
    private boolean can_access_closed;
    private boolean is_closed;

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + "\"" + id + "\"" +
                ", \"city\":" + "\"" + city + "\"" +
                ", \"first_name\":" + "\"" + first_name + "\"" +
                ", \"last_name\":" + "\"" + last_name + "\"" +
                ", \"can_access_closed\":" + "\"" + can_access_closed + "\"" +
                ", \"is_closed\":" + "\"" + is_closed + "\"" +
                '}';
    }
}
