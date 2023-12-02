package com.example.authentication;

import com.example.authentication.model.Screen;
import com.example.authentication.service.IScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@RestController
public class AuthenticationApplication implements CommandLineRunner {

	@Autowired
	private IScreenService screenService;



	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApplication.class, args);
	}

	@GetMapping("/hello")
	public String sayHello() {
		return "Hello from the Authentication Application!";
	}



	@Override
	public void run(String... args) throws Exception {
		screenService.createScreen("Home");
		screenService.createScreen("CarManagement");

	}
}
