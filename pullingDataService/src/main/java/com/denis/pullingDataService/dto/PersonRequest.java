package com.denis.pullingDataService.dto;

import lombok.Builder;

@Builder
public record PersonRequest(String name) {
}
