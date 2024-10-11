package com.denis.vkService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VkServiceApplicationTests {
	@LocalServerPort
	private int port;

	private String VK_CONTROLLER_URL;

	@BeforeEach
	public void setVkControllerUrl(){
		this.VK_CONTROLLER_URL = "http://localhost:" + this.port + "/api/vk";
	}

	@Test
	void test() {

	}
}
