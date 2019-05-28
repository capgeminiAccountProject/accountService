package com.org.cap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.org.cap"})
public class CapAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapAccountApplication.class, args);
	}

}
