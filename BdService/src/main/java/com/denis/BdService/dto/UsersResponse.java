package com.denis.BdService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsersResponse {

    private List<UserEntity> response;

    @Override
    public String toString() {
        return response.toString();
    }

    public int getCountOfUsers(){
        return this.response.size();
    }
}