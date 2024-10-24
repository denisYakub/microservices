package com.denis.pullingDataService.dto;

import lombok.Builder;

@Builder
public record vkUsersRequest(int[] ids, String[] fields) {
}
