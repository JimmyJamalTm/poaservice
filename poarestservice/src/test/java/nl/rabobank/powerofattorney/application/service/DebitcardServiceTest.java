package nl.rabobank.powerofattorney.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.rabobank.powerofattorney.application.model.Debitcard;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DebitcardServiceTest {

    @Mock
    ObjectMapper objectMapper;

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    private DebitcardService debitcardService;

    @Test
    void testRetrieveDebitcard() throws Exception{
        Debitcard debitcard = new Debitcard();
        debitcard.setId("testId");
        final String uri = "http://localhost:8080/debit-cards/" + debitcard.getId();
        final String testString = "test";

        when(restTemplate.getForObject(eq(uri), eq(String.class))).thenReturn(testString);
        when(objectMapper.readValue(eq(testString), eq(Debitcard.class))).thenReturn(debitcard);

        assertEquals(debitcardService.retrieveDebitcard(debitcard.getId()), debitcard);
    }
}
