package com.example.userservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.save(new User(1L, "Nguyen Van A", "123 Le Loi, Q1"));
        userRepository.save(new User(2L, "Tran Thi B", "456 Nguyen Hue, Q1"));
    }
}
