package com.denis.vkService.dto;

public record UserRecord(int id, String first_name, String last_name,
                         boolean can_access_closed, boolean is_closed) {
}
