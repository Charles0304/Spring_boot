package edu.pnu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"edu.pnu","test.pnu"})
public class Mission03Application {

	public static void main(String[] args) {
		SpringApplication.run(Mission03Application.class, args);
	}

}
