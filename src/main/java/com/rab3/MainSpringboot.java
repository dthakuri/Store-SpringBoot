package com.rab3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.rab3.controller.AuthController;
import com.rab3.controller.ProfileController;

@SpringBootApplication
@ComponentScan(basePackageClasses=AuthController.class)
@ComponentScan(basePackageClasses=ProfileController.class)
public class MainSpringboot {
	public static void main(String[] args) {
		SpringApplication.run(MainSpringboot.class, args);
	}

}
