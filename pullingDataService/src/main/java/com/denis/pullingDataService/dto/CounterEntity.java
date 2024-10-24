package com.denis.pullingDataService.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CounterEntity {
    private int id;

    private int albums;
    private int videos;
    private int audios;
    private int photos;
    private int notes;
    private int friends;
    private int gifts;
    private int groups;
    private int online_friends;
    private int mutual_friends;
    private int user_videos;
    private int user_photos;
    private int followers;
    private int pages;
    private int subscriptions;

    @Override
    public String toString() {
        return "{" +
                "\"albums\":" + "\"" + albums + "\"" +
                ", \"videos\":" + "\"" + videos + "\"" +
                ", \"audios\":" + "\"" + audios + "\"" +
                ", \"photos\":" + "\"" + photos + "\"" +
                ", \"notes\":" + "\"" + notes + "\"" +
                ", \"friends\":" + "\"" + friends + "\"" +
                ", \"gifts\":" + "\"" + gifts + "\"" +
                ", \"groups\":" + "\"" + groups + "\"" +
                ", \"online_friends\":" + "\"" + online_friends + "\"" +
                ", \"mutual_friends\":" + "\"" + mutual_friends + "\"" +
                ", \"user_videos\":" + "\"" + user_videos + "\"" +
                ", \"user_photos\":" + "\"" + user_photos + "\"" +
                ", \"followers\":" + "\"" + followers + "\"" +
                ", \"pages\":" + "\"" + pages + "\"" +
                ", \"subscriptions\":" + "\"" + subscriptions + "\"" +
                '}';
    }
}
