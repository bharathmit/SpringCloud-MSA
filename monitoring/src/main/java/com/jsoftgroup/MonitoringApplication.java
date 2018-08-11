package com.jsoftgroup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

import de.codecentric.boot.admin.server.config.EnableAdminServer;


@SpringBootApplication
@EnableHystrixDashboard
@EnableDiscoveryClient
@EnableAdminServer
public class MonitoringApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MonitoringApplication.class, args);
	}
}
