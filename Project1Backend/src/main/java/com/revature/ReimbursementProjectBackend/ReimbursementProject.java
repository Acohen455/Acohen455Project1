package com.revature.ReimbursementProjectBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//the scanbasepackages parameter is to specify where to scan for beans
//we need this because we have multiple packages with beans -- ie. this primary source isnt in the same package as all our sub package
@SpringBootApplication(scanBasePackages = "com.revature")
@EntityScan("com.revature.models")
public class ReimbursementBackendApplication {

	//TODO: call the application
	public static void main(String[] args) {
		SpringApplication.run(P1DemoBackendApplication.class, args);
	}
	//this bootstraps the application -- we pass into .run the primary source class (ie. the root) and the command line arguments
	//the primary source class is generally the class with @SpringBootApplication, in most cases the main class and the one run is called in
	//convention is for both run and the application tag to be in the same class
		SpringApplication.run(ReimbursementBackendApplication.class, args);
}
