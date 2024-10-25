package com.denis.BdService.dto;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "languages")
@Data
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LanguageEntity {
    @Id
    @Column(unique = true)
    private int id;

    private String native_name;

    @ManyToOne
    @JoinColumn(name = "personal_id")
    private PersonalEntity personal;

    @Override
    public String toString(){
        return "{" +
                "\"id\"" + "\"" + id + "\"" +
                "\"native_name\"" + "\"" + native_name + "\"" +
                "}";
    }
}
