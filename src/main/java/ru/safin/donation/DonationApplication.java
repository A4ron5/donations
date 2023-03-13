package ru.safin.donation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class DonationApplication {

	public static void main(String[] args) {
		SpringApplication.run(DonationApplication.class, args);
	}

}
