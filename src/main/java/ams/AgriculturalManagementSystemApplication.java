package ams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("ams.*")
public class AgriculturalManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgriculturalManagementSystemApplication.class, args);
	}

}
