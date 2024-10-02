package com.pet.mypet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class MypetApplication {

	public static void main(String[] args) {
		SpringApplication.run(MypetApplication.class, args);
	}
}
