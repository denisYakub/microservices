package com.denis.vkService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VkServiceApplicationTests {
	private String VK_CONTROLLER_URL;
	@LocalServerPort
	private int port;
	@Autowired
	private TestRestTemplate restTemplate;

	@BeforeEach
	public void setVkControllerUrl(){
		this.VK_CONTROLLER_URL = "http://localhost:" + this.port + "/api/vk";
	}

	@Test
	void testCheckingGettingUrlForAccessToken() {

		var response = this.restTemplate.getForEntity(this.VK_CONTROLLER_URL + "/accessToken", String.class);
		System.out.println(response);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo("https://oauth.vk.com/authorize?response_type=token&client_id=52411376&state=photos,groups&scope=offlinedisplay=page&v=5.199");

	}

    @Test
	void testCheckingNonValidAccessToken() throws Exception{
		var response = this.restTemplate.postForEntity(this.VK_CONTROLLER_URL + "/BAD_ACCESS_TOKEN",
				null, String.class);

		assertThat(response.getBody()).isEqualTo("Access token isn't valid");
	}

	@Test
	void testCheckingValidAccessTokenFromEnv() throws Exception{
		var response = this.restTemplate.postForEntity(this.VK_CONTROLLER_URL + System.getenv("ACCESS_TOKEN"),
				null, String.class);

		assertThat(System.getenv("ACCESS_TOKEN")).isNotEmpty();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
	}
}
