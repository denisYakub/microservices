package com.denis.pullingDataService.controller;

import com.denis.pullingDataService.service.PullService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PullController.class)
public class PullControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private PullService pullService;

    @Test
    public void test_correct_api_get() throws Exception{
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/pull")
                        .param("fromId", String.valueOf(20))
                        .param("toId", String.valueOf(40)))
                .andExpect(status().isCreated());

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/pull/100"))
                .andExpect(status().isCreated());

        verify(pullService, times(1)).startPulling(20, 40);
        verify(pullService, times(1)).startPulling(0, 100);
    }

    @BeforeEach
    public void setUp(){
        doNothing().when(this.pullService).startPulling(any(Integer.class), any(Integer.class));
    }
}
