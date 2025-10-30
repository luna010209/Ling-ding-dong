package com.example.lingdingdong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LingdingdongApplication {

	public static void main(String[] args) {
		SpringApplication.run(LingdingdongApplication.class, args);
	}

}
