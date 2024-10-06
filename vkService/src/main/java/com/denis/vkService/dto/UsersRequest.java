package com.denis.vkService.dto;

import lombok.Builder;

import java.util.Arrays;

@Builder
public record UsersRequest(int[] ids, String[] fields) {
    public String getStringifyIds(){
        return Arrays.toString(this.ids)
                .replace(" ", "")
                .replace("[", "")
                .replace("]", "");
    }
    public String getStringifyFields(){
        return Arrays.toString(this.fields)
                .replace(" ", "")
                .replace("[", "")
                .replace("]", "");
    }
}
