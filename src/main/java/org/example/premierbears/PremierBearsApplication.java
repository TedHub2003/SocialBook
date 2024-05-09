package org.example.premierbears;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PremierBearsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PremierBearsApplication.class, args);
	}

}
