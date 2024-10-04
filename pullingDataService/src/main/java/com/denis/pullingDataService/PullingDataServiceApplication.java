package com.denis.pullingDataService;

import com.denis.pullingDataService.controller.Controller;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PullingDataServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(PullingDataServiceApplication.class, args);
	}
}
