package com.denis.pullingDataService.service;

import com.denis.pullingDataService.configuration.Config;
import com.denis.pullingDataService.dto.vkUsersRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PullServiceTests {
    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private PullService pullService;

    private final int threads = 8;
    private final int chunk = 1000;
    private final String mocked_url = "http://localhost:8080/mocked";

    @Test
    public void test_startPulling_method(){
        PullService service = Mockito.spy(pullService);
        doNothing().when(service).startMultiThreadDownloading(anyInt(), anyInt());

        int fromId = 1;
        int toId = 10;

        service.startPulling(fromId, toId);

        verify(service).startMultiThreadDownloading(fromId, toId);
    }

    @Test
    public void test_startMultiThreadDownloading_method(){

    }

    @Test
    public void test_getArrayOfIds_method(){
        int fromId = 5;
        int toId = 10;
        int[] expected = {5, 6, 7, 8, 9, 10};

        int[] result = pullService.getArrayOfIds(fromId, toId);

        assertArrayEquals(expected, result, "Array of IDs should match the expected values.");

    }

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

        ReflectionTestUtils.setField(pullService, "URL_VK_SERVICE", mocked_url);
        ReflectionTestUtils.setField(pullService, "URL_KAFKA_SERVICE", mocked_url);
        ReflectionTestUtils.setField(pullService, "NUMBER_OF_THREADS", threads);
        ReflectionTestUtils.setField(pullService, "CHUNK_SIZE", chunk);

    }
}
