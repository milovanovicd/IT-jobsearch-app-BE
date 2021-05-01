package com.jobsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.jobsearch.config.FileStorageConfig;

@SpringBootApplication
@EnableConfigurationProperties({
	FileStorageConfig.class
})
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
		System.out.println("JOBSEARCH App up and running!");
	}

}
