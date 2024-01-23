package com.tp3.accountservice;

import com.tp3.accountservice.clients.CustomerRestClient;
import com.tp3.accountservice.entities.BankAccount;
import com.tp3.accountservice.enums.AccountType;
import com.tp3.accountservice.repository.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BankAccountRepository bankAccountRepository, CustomerRestClient customerRestClient){

        return args -> {
            customerRestClient.allCustomers().forEach(c ->{
                BankAccount bankAccount1=BankAccount.builder()
                        .accountId(UUID.randomUUID().toString())
                        .currency("MAD")
                        .balance(Math.random()*10000)
                        .createAt(LocalDate.now())
                        .customerId(c.getId())
                        .type(AccountType.SAVING_ACCOUNT)
                        .build();

                BankAccount bankAccount2=BankAccount.builder()
                        .accountId(UUID.randomUUID().toString())
                        .currency("MAD")
                        .balance(Math.random()*10000)
                        .createAt(LocalDate.now())
                        .customerId(c.getId())
                        .type(AccountType.CURRENT_ACCOUNT)
                        .build();

                bankAccountRepository.save(bankAccount1);
                bankAccountRepository.save(bankAccount2);
            });
        };
    }

}
