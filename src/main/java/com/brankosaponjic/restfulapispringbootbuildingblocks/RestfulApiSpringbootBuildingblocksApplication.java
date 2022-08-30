package com.brankosaponjic.restfulapispringbootbuildingblocks;

import com.brankosaponjic.restfulapispringbootbuildingblocks.entities.User;
import com.brankosaponjic.restfulapispringbootbuildingblocks.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestfulApiSpringbootBuildingblocksApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestfulApiSpringbootBuildingblocksApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(UserRepository repository) {
        return (args) -> {
            insertThreeUsers(repository);
            System.out.println(repository.findAll());
        };
    }
    private void insertThreeUsers(UserRepository repository) {
        repository.save(new User(101L, "kreddy","Kalyan", "Reddy",  "kreddy@stacksimplify.com", "admin", "ssn101"));
        repository.save(new User(102L, "gwiser", "Greg", "Wiser", "gwiser@stacksimplify.com", "admin", "ssn102"));
        repository.save(new User(103L, "dmark", "David", "Mark", "dmark@stacksimplify.com", "admin", "ssn103"));

    }

}
