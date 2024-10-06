package com.denis.pullingDataService.dto;

import java.io.Serializable;
import java.util.List;

public class UsersResponse implements Serializable {
    public static class User{
        int id;
        String first_name;
        String last_name;
        boolean can_access_closed;
        boolean is_closed;
    }

    public List<User> response;

    @Override
    public String toString(){
        StringBuilder resultStr = new StringBuilder("[\n");
        for(var user: response){
            resultStr.append("{")
                    .append("\"id\": ").append(user.id).append("\t")
                    .append("\"first_name\": ").append(user.first_name).append("\t")
                    .append("\"last_name\": ").append(user.last_name).append("\t")
                    .append("},\n");
        }

        return resultStr.append("]").toString();
    }

}
