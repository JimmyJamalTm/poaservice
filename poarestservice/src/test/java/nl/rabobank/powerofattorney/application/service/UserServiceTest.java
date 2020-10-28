package nl.rabobank.powerofattorney.application.service;

import nl.rabobank.powerofattorney.application.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void test() {

    }
}