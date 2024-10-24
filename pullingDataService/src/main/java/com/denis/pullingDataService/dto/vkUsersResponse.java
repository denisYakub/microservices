package com.denis.pullingDataService.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class vkUsersResponse {

    private List<UserEntity> response;

    @Override
    public String toString() {
        return response.toString();
    }

    public int getCountOfUsers(){
        return this.response.size();
    }
}