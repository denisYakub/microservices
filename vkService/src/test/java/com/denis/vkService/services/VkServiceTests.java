package com.denis.vkService.services;

import com.denis.vkService.controller.VkController;
import com.denis.vkService.dto.vkUsersRequest;
import com.denis.vkService.service.VkService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.net.http.HttpClient;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class VkServiceTests {
    @Autowired
    private VkService vkService;

    private final vkUsersRequest request = new vkUsersRequest(new int[]{1, 2, 3}, new String[] {"bdate, nickname"});

    @Test
    public void test_getUsersBasicInfoFromVkApiBy_method_if_access_token_invalid() {
        vkService = new VkService();
        ReflectionTestUtils.setField(vkService, "ACCESS_TOKEN", "INVALID_ACCESS_TOKEN");

        try {
            var response = this.vkService.getUsersBasicInfoFromVkApiBy(request.getStringifyIds(), request.getStringifyFields());
            fail("Код не должен работать без выброса ошибки");
        } catch (RuntimeException e) {
            assertThat(e.getMessage()).contains("invalid access_token");
        } catch (Exception e){
            fail("Код не должен выбрасывать будой тип ошибок");
        }
    }

    @Test
    public void test_getUsersBasicInfoFromVkApiBy_method() {
        var response = this.vkService.getUsersBasicInfoFromVkApiBy(request.getStringifyIds(), request.getStringifyFields());

        assertThat(response)
                .contains("\"id\":1").contains("\"id\":2").contains("\"id\":3")
                .contains("\"bdate\":")
                .contains("\"nickname\":");
    }

    @Test
    public void test_getAccessTokenForVkRequests_method(){
        var response = this.vkService.getAccessTokenForVkRequests();

        assertThat(response).isNotEmpty();
    }
}
