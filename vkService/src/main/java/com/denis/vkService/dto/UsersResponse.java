package com.denis.vkService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

public class UsersResponse implements Serializable {
    public static class User implements Serializable {
        int id;
        String first_name;
        String last_name;
        boolean can_access_closed;
        boolean is_closed;
    }

    public List<User> response;

    public void printFirstUser(){
        System.out.println(this.response.get(0).id + " " + this.response.get(0).first_name + " " + this.response.get(0).last_name);
    }

}
