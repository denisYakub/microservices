package com.denis.pullingDataService;

import com.denis.pullingDataService.configuration.Config;
import com.denis.pullingDataService.dto.CityEntity;
import com.denis.pullingDataService.dto.UserEntity;
import com.denis.pullingDataService.dto.UsersResponse;
import com.denis.pullingDataService.service.PullService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PullingDataServiceApplicationTests {
	private String PULL_CONTROLLER_URL;
	private final String URL_VK_SERVICE = "http://localhost:80/api/vk";

	@Mock
	private Config config;
	@Mock
	private RestTemplate restTemplate;
	@LocalServerPort
	private int port;
	@Autowired
	@InjectMocks
	private PullService pullService;

	@BeforeEach
	public void setPullControllerUrl(){
		this.PULL_CONTROLLER_URL = "http://localhost:" + this.port + "/api/pull/";
		MockitoAnnotations.openMocks(this);
		when(config.restTemplate()).thenReturn(restTemplate);
	}

	@Test
	void testPullingUsersByNumberOfUsers() throws Exception{
		int numberOfUsers = 16000;

		when(restTemplate.postForEntity(any(String.class), any(), any(Class.class)).getBody()).thenReturn(this.getUsersResponse(0, 16000));

		List<UsersResponse> result = this.pullService.splitAndStartMultiThreadDownloading(0, numberOfUsers);

		assertThat(this.countUsersInListOfUsersResponse(result)).isEqualTo(numberOfUsers);
	}

	private String getUsersResponse(int from, int to){
		List<UserEntity> users = new ArrayList<>();

		for(int i = from; i < to; i++){
			users.add(
					UserEntity.builder()
							.id(i)
							.first_name("bot_" + i)
							.last_name("bot_" + i)
							.can_access_closed(true)
							.is_closed(true)
							.city(
									CityEntity.builder()
											.id(-1)
											.title("bot_city")
											.build()
							)
							.build()
			);
		}

		return new UsersResponse(users).toString();
	}

	private int countUsersInListOfUsersResponse(List<UsersResponse> usersResponseList){
		int totalNumber = 0;
		for(var listOfUsers: usersResponseList){
			totalNumber += listOfUsers.getCountOfUsers();
		}
		return totalNumber;
	}

}
