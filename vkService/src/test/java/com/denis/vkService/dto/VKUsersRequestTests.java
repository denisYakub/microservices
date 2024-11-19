package com.denis.vkService.dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class VKUsersRequestTests {
    private final vkUsersRequest request = new vkUsersRequest(new int[]{1, 2, 3}, new String[] {"bdate, nickname"});

    @Test
    public void test_vkUsersRequest_dto(){
        assertEquals(request.getStringifyIds(), "1,2,3");
        assertEquals(request.getStringifyFields(), "bdate,nickname");
    }
}
