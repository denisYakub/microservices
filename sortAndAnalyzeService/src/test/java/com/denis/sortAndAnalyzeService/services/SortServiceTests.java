package com.denis.sortAndAnalyzeService.services;

import com.denis.sortAndAnalyzeService.configuration.Config;
import com.denis.sortAndAnalyzeService.service.SortService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SortServiceTests {
    @Autowired
    private SortService sortService;

    /*@Test
    public void test_deleteUsersWithClosedAccount_method(){
        ReflectionTestUtils.setField(sortService, "URL_BD_SERVICE", "mocked");

        doNothing().when(Config.restTemplate()).exchange(
                anyString(),
                HttpMethod.DELETE,
                any(HttpEntity.class),
                Void.class);

        this.sortService.deleteUsersWithClosedAccount();
    }*/
}
