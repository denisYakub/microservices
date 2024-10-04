package com.denis.vkService;

import com.denis.vkService.service.VkService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

//@SpringBootApplication
public class VkServiceApplication {

	public final static VkService vkService = new VkService();

	public static void main(String[] args) {
		//SpringApplication.run(VkServiceApplication.class, args);
		vkService.getUserInfo();
	}
}
