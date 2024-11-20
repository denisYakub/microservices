package com.denis.BdService.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UsersRequest {

    private List<UserEntity> response;

    @Override
    public String toString() {
        return "{\"response\":" + String.join("} ,{", response.toString()) + "}";
    }

    public int getCountOfUsers(){
        return this.response.size();
    }
}