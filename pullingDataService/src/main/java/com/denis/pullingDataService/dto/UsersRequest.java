package com.denis.pullingDataService.dto;

import lombok.Builder;

@Builder
public record UsersRequest(int[] ids, String[] fields) {
}
