package com.accenture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;

@SpringBootApplication
@EnableHystrixDashboard
@EnableZuulServer
public class MonitoringApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitoringApplication.class, args);
	}
}
