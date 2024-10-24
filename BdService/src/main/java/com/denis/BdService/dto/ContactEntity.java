package com.denis.BdService.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Entity
@Table(name = "contacts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactEntity {
    @Id
    @Column(unique = true)
    private int id;

    private String mobile_phone;
    private String home_phone;

    @Override
    public String toString() {
        return "{" +
                "\"mobile_phone\":" + "\"" + mobile_phone + "\"" +
                ", \"home_phone\":" + "\"" + home_phone + "\"" +
                '}';
    }
}
