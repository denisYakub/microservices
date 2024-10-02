package com.pet.mypet.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "cl1")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Cl1 {
    @Id
    private String id;
    private String name;
}
