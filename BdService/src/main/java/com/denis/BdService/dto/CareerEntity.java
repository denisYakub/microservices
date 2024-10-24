package com.denis.BdService.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Entity
@Table(name = "careers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CareerEntity {
    @Id
    @Column(unique = true)
    private int id;

    private int group_id;
    private String company;

    private int city_id;
    private String city_name;

    private int start;
    private int until;

    private String position;

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
