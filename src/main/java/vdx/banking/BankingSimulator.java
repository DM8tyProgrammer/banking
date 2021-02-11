package vdx.banking;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import vdx.banking.user.User;
import vdx.banking.user.UserAccountFunction;
import vdx.banking.user.UserRepository;
import vdx.banking.user.account.Account;
import vdx.banking.user.transaction.Transaction;

import java.util.Collection;


@SpringBootApplication
public class BankingSimulator {

    Logger log = LoggerFactory.getLogger(BankingSimulator.class);

    public static void main(String[] args) {
        SpringApplication.run(BankingSimulator.class, args);
    }


    @Bean
    public CommandLineRunner demo(BankingInterface bankingFunction, UserRepository userRepository) {
        return (args) -> {

            log.info("Creating Users ");
            userRepository.save(new User("Jhon", "Doe", new Account(20)));
            userRepository.save(new User("Jane", "Doe", new Account(10)));

            log.info("-------------------------------");

            log.info("Users with initial Balance:");
            for (User user : userRepository.findAll()) {
                log.info(user.toString());
                log.info(" balance : {}", user.getAccount().getBalance());
            }
            log.info("-------------------------------");
            log.info("Updating balance of user : Jane ");


            User jane = new User("Jane", "Doe");
            double newBalance = bankingFunction.updateBalance(jane, 30);
            log.info("Balance updated to {} ", newBalance);


            log.info("-------------------------------");
            log.info("Getting transaction of  Jane ");

            Collection<Transaction> transactions = bankingFunction.generateMiniStatement(jane);

            for (Transaction t: transactions) {
                log.info(" -- {}", t);
            }


            log.info("-------------------------------");
            log.info("Transferring from  Jane to Jhon ");

            User jhon = new User("Jhon", "Doe");
            bankingFunction.transfer(jane, jhon, 10);

            log.info("Updated balance of Jhon {}", bankingFunction.getBalance(jhon));


        };
    };
}
