package com.pet.mypet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pet.mypet.dto.Cl1Request;
import com.pet.mypet.repositories.Cl1Repository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class MypetApplicationTests {

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private Cl1Repository cl1Repository;

	private final String apiUrl = "/api/cl1";

	@DynamicPropertySource
	static void setPropertiesForMongoDB(DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

	@Test
	void createCl1() throws Exception {

		Cl1Request cl1Request = Cl1Request.builder()
				.name("denis")
				.build();

		String cl1RequestString = objectMapper.writeValueAsString(cl1Request);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/cl1")
					.contentType(MediaType.APPLICATION_JSON)
					.content(cl1RequestString))
				.andExpect(status().isCreated());

        Assertions.assertEquals(1, cl1Repository.findAll().size());
	}

}
