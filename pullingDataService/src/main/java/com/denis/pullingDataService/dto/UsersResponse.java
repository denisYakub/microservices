package com.denis.pullingDataService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

public class UsersResponse {
    public class User{
        public class City{
            public int id;
            public String title;

            @Override
            public String toString() {
                return "{" +
                        "\"id\":" + "\"" + id + "\"" +
                        ", \"title\":" + "\"" + title + "\"" +
                        '}';
            }
        }

        public int id;
        public City city;
        public String first_name;
        public String last_name;
        public boolean can_access_closed;
        public boolean is_closed;

        @Override
        public String toString() {
            return "{" +
                    "\"id\":" + "\"" + id + "\"" +
                    ", \"city\":" + "\"" + city + "\"" +
                    ", \"first_name\":" + "\"" + first_name + "\"" +
                    ", \"last_name\":" + "\"" + last_name + "\"" +
                    ", \"can_access_closed\":" + "\"" + can_access_closed + "\"" +
                    ", \"is_closed\":" + "\"" + is_closed + "\"" +
                    '}';
        }
    }

    public List<User> response;

    @Override
    public String toString() {
        return response.toString();
    }

    public int getCountOfUsers(){
        return this.response.size();
    }

}
