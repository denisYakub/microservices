package com.denis.sortingDataService.dto;

import lombok.Builder;

@Builder
public record PersonRequest(int id, String name) {
}
