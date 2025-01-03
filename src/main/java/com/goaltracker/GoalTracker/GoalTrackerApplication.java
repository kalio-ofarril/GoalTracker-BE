package com.goaltracker.GoalTracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GoalTrackerApplication {

	// @LoadBalanced
	// @Bean
	// public RestTemplate restTemplate() {
	// return new RestTemplate();
	// }

	public static void main(String[] args) {
		SpringApplication.run(GoalTrackerApplication.class, args);
	}

}
