package com.denis.BdService.dto;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "careers")
@Data
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CareerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int group_id;
    private String company;

    private int city_id;
    private String city_name;

    private int start;
    private int until;

    private String position;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Override
    public String toString() {
        return "{" +
                "\"group_id\":" + "\"" + group_id + "\"" +
                ", \"company\":" + "\"" + company + "\"" +
                ", \"city_id\":" + "\"" + city_id + "\"" +
                ", \"city_name\":" + "\"" + city_name + "\"" +
                ", \"from\":" + "\"" + start + "\"" +
                ", \"until\":" + "\"" + until + "\"" +
                ", \"position\":" + "\"" + position + "\"" +
                '}';
    }
}
