package com.revature.ReimbursementProjectBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//the scanbasepackages parameter is to specify where to scan for beans
//we need this because we have multiple packages with beans -- ie. this primary source isnt in the same package as all our sub package
@SpringBootApplication(scanBasePackages = "com.revature")
@EntityScan("com.revature.models")
@ComponentScan("com.revature")
@EnableJpaRepositories("com.revature.DAOs")
public class ReimbursementBackendApplication {

	//TODO: call the application
	public static void main(String[] args) {
		SpringApplication.run(ReimbursementBackendApplication.class, args);
	}
}
