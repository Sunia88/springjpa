package com.suniacode.java.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

@SpringBootApplication
public class JpaApplication {

    private static final Logger LOG = LoggerFactory.getLogger(JpaApplication.class);

	public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
	}

    @Bean
    public CommandLineRunner init(CustomerRepository customerRepository) {
        return (args) -> {
            //save view customer
            customerRepository.save(new Customer("John", "Doe"));
            customerRepository.save(new Customer("Jane", "Doe"));
            customerRepository.save(new Customer("Mary", "Doe"));
            customerRepository.save(new Customer("Jane", "Doe"));
            customerRepository.save(new Customer("Jane", "Doe"));

            //fetch all customer
            LOG.info("Customers found with findAll()");
            LOG.info("------------------------------");
            for (Customer customer : customerRepository.findAll()) {
                LOG.info(customer.toString());
                LOG.info("");
            }

            // fetch an individual customer by ID
            Customer customer = customerRepository.findById(1L);
            LOG.info("Customer found with findById(1L)");
            LOG.info("--------------------------------");
            LOG.info(customer.toString());
            LOG.info("");

            // fetch customers by last name
            LOG.info("Customer found with findByLastName('Doe'):");
            LOG.info("-------------------------------------------");
            customerRepository.findByLastName("Doe").forEach(doe -> LOG.info(doe.toString()));
            LOG.info("");
        };
    }

}
