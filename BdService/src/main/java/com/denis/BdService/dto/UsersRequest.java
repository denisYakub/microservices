package com.denis.BdService.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record UsersRequest(List<UserEntity> response) {

    @Override
    public String toString() {
        return response.toString();
    }

    public int getCountOfUsers(){
        return this.response.size();
    }
}
