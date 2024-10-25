package com.denis.BdService.dto;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "relatives")
@Data
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RelativeEntity {
    private enum type{
        child, sibling, parent, grandparent, grandchild
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + "\"" + id + "\"" +
                ", \"name\":" + "\"" + name + "\"" +
                ", \"type\":" + "\"" + type + "\"" +
                '}';
    }
}
