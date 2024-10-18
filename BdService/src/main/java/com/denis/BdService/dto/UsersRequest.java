package com.denis.BdService.dto;

import lombok.Builder;

@Builder
public record UsersRequest(int[] ids, String[] fields) {
}
