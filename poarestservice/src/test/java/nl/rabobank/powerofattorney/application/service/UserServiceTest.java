package nl.rabobank.powerofattorney.application.service;

import nl.rabobank.powerofattorney.application.model.User;
import nl.rabobank.powerofattorney.application.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {


    @Mock
    UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testRetrieveAccount() throws Exception{
        User user = new User();
        user.setUsername("test1");

        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));

        assertEquals(userService.loadUserByUsername(user.getUsername()), user);
    }
}