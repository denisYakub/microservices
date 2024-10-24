package com.denis.vkService;

import com.denis.vkService.controller.VkController;
import com.denis.vkService.dto.vkUsersRequest;
import com.denis.vkService.service.VkService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VkServiceApplicationTests {
	@Autowired
	private VkService vkService;
	@Autowired
	private VkController vkController;

	@LocalServerPort
	private int port;

	@Test
	void testGetUserInfoController() {
		vkUsersRequest requestBody = new vkUsersRequest(
				new int[]{1, 2, 3, 4},
				new String[]{"city", "education"}
		);

		String response = this.vkController.getUsersInfoJsonBy(requestBody);

		assertThat(response)
				.contains("\"id\":1")
				.contains("\"city\":")
				.contains("\"university\":")
				.contains("\"id\":2")
				.contains("\"id\":3")
				.contains("\"id\":4");
	}

	@Test
	void testGetUserInfoWrongBodyController() {
		//String response = this.vkController.getUsersInfoJsonBy(null);
		//assertThat(response.get);
	}

	@Test
	void testGetAccessTokenService() {
		URI response = URI.create(this.vkService.getAccessTokenForVkRequests());
		//TODO сделать проверку ссылки
	}

	@Test
	void testGetUsersInfoJsonService() {
		String response = this.vkService.getUsersBasicInfoFromVkApiBy(
				"1,2",
				"city,education"
		);

		assertThat(response)
				.contains("\"id\":1")
				.contains("\"city\":{")
				.contains("\"university\":1")
				.contains("\"id\":2");
	}
}
