package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan
public class ElibraryApplication implements CommandLineRunner {

	@Override
	public void run(String... args) {
		System.out.println("Application Starting");
	}

	public static void main(String[] args) {
		SpringApplication.run(ElibraryApplication.class, args);
	}
}

