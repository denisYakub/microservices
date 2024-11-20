package com.denis.BdService.controller;

import com.denis.BdService.dto.FieldToDeleteBy;
import com.denis.BdService.dto.FieldsToDeleteBy;
import com.denis.BdService.service.PostgresqlService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BdController.class)
public class BdControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private PostgresqlService postgresqlService;

    @Test
    public void test_correct_api_get() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/api/bd")
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString("request")))
                .andExpect(status().isOk());
    }

    @Test
    public void test_correct_api_delete() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/bd")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(new FieldsToDeleteBy<>(
                                new FieldToDeleteBy<>("nickname", "bod"),
                                new FieldToDeleteBy<>("bdate", "12.05.2001")
                        ))))
                .andExpect(status().isOk());
    }

    @BeforeEach
    public void setUp(){
        doNothing().when(this.postgresqlService).saveUsersRequest(any(String.class));
        doNothing().when(this.postgresqlService).deleteUsers(any(FieldsToDeleteBy.class));
    }
}
