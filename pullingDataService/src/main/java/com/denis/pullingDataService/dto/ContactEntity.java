package com.denis.pullingDataService.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactEntity {
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
