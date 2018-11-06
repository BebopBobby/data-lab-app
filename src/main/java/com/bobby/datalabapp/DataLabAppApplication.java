package com.bobby.datalabapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bobby.datalabapp.Customer;
import com.bobby.datalabapp.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DataLabAppApplication {

	private static final Logger log = LoggerFactory.getLogger(DataLabAppApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DataLabAppApplication.class, args);
	}


	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return args -> {
			repository.save(new Customer("Zach", "Rivera"));
			repository.save(new Customer("Derian", "Williams"));
			repository.save(new Customer("Lamar", "Witherspoon"));

			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			repository.findById(1L)
					.ifPresent(customer -> {
						log.info("Customer found with findById(1L):");
						log.info("--------------------------------");
						log.info(customer.toString());
						log.info("");
					});

			log.info("Customer found with findByLastName('Rivera'):");
			log.info("--------------------------------------------");
			repository.findByLastName("Rivera").forEach(rivera -> {
				log.info(rivera.toString());
			});
		};

	}
}
