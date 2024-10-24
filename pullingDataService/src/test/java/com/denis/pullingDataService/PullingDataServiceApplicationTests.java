package com.denis.pullingDataService;

import com.denis.pullingDataService.configuration.Config;
import com.denis.pullingDataService.service.PullService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PullingDataServiceApplicationTests {
	@Mock
	private Config config;
	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private PullService pullService;

	@Value("${global.numberOfThreads}")
	private int NUMBER_OF_THREADS;

	@BeforeEach
	void setUp(){
		MockitoAnnotations.openMocks(this);
		pullService = new PullService(config);
	}

	@Test
	void testStartPulling_CallsSplitAndStartMultiThreadDownloading() throws ExecutionException, InterruptedException {
		int fromId = 1;
		int toId = 20000;

		int expected = toId - fromId + 1;

		when(config.restTemplate()).thenReturn(restTemplate);

		pullService.startPulling(fromId, toId);

		for(int i = fromId; i <= toId; i += this.NUMBER_OF_THREADS * 1000){
			int threadFromId = i;
			int threadToId = Math.min(threadFromId + (this.NUMBER_OF_THREADS * 1000) - 1, toId);

			verify(restTemplate, times(1))
					.postForEntity(any(String.class), any(), eq(String.class));
		}

		int[] ids = pullService.getArrayOfIds(fromId, toId);
		assertEquals(expected, ids.length);
	}

	@Test
	void testSplitAndStartMultiThreadDownloadingFunction(){

	}
}
