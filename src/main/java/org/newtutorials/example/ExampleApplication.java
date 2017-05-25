package org.newtutorials.example;

import org.newtutorials.example.model.Customer;
import org.newtutorials.example.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;

/**
 * Created by dani on 25/05/2017.
 */
@SpringBootApplication
@EnableTransactionManagement
public class ExampleApplication {
    private static final Logger logger = LoggerFactory.getLogger(ExampleApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }

    @Bean
    public CommandLineRunner exampleRunner(CustomerService customerService) {
        return (args) -> {
            Customer customer = new Customer("Customer 1", new ArrayList<>());
            customer.getOrders().add(new Order(customer, "order 1"));
            customer.getOrders().add(new Order(customer, "order 2"));
            customerService.save(customer);
            customerService.findAll().forEach(c -> {
                logger.info(c.toString());
            });
        };
    }
}
