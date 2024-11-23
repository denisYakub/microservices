package com.denis.sortAndAnalyzeService.controller;

import com.denis.sortAndAnalyzeService.service.AnalyzeService;
import com.denis.sortAndAnalyzeService.service.SortService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SortAndAnalyzeController.class)
public class SortAndAnalyzeControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private SortService sortService;
    @MockBean
    private AnalyzeService analyzeService;

    @BeforeEach
    public void setUpTests(){
        doNothing().when(sortService).deleteUsersWithClosedAccount();
    }

    @Test
    public void test_correct_api_delete() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/sortAnalyze" + "/cleanUsersWithClosedAcc"))
                .andExpect(status().isOk());
    }
}
