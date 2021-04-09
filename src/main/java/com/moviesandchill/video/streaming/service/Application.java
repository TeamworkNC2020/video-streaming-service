package com.moviesandchill.video.streaming.service;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan
@EntityScan(basePackages = {"com.moviesandchill.video.streaming.service.domain" })
@EnableJpaRepositories(basePackages = {"com.moviesandchill.video.streaming.service.repository"})

public class Application {

	public static void main(String[] args) {
            SpringApplication.run(Application.class, args);

                        
	}
}