package com.denis.vkService.controller;

import com.denis.vkService.dto.vkUsersRequest;
import com.denis.vkService.service.VkService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VkController.class)
public class VkServiceControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private VkService vkService;

    private final vkUsersRequest request = new vkUsersRequest(new int[]{1, 2, 3}, new String[] {"bdate, nickname"});

    @BeforeEach
    public void setUpTests(){
        when(vkService.getAccessTokenForVkRequests()).thenReturn("done");
        when(vkService.getUsersBasicInfoFromVkApiBy(any(), any())).thenReturn("done");
    }

    @Test
    public void test_correct_api_get() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/api/vk"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("done"));
    }

    @Test
    public void test_correct_api_post() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/vk")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("done"));
    }
}
