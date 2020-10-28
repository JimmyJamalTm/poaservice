package nl.rabobank.powerofattorney.application.service;

import nl.rabobank.powerofattorney.application.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void test() {

    }
}