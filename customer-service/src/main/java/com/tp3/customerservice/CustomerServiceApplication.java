package com.tp3.customerservice;

import com.tp3.customerservice.config.GlobalConfig;
import com.tp3.customerservice.entities.Customer;
import com.tp3.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(GlobalConfig.class)
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository){

        return args -> {


            Customer customer1=Customer.builder()
                    .firstName("Hassan")
                    .lastName("HHH")
                    .email("hassan@gmail.com")
                    .build();
            customerRepository.save(customer1);

            Customer customer2=Customer.builder()
                    .firstName("Ali")
                    .lastName("AAA")
                    .email("ali@gmail.com")
                    .build();
            customerRepository.save(customer2);
        };
    }

}

