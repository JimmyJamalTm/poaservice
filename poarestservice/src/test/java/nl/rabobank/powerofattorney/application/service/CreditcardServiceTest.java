package nl.rabobank.powerofattorney.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.rabobank.powerofattorney.application.model.Creditcard;
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
public class CreditcardServiceTest {

    @Mock
    ObjectMapper objectMapper;

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    private CreditcardService creditcardService;

    @Test
    void testRetrieveCreditcard() throws Exception{
        Creditcard creditcard = new Creditcard();
        creditcard.setId("testId");
        final String uri = "http://localhost:8080/credit-cards/" + creditcard.getId();
        final String testString = "test";

        when(restTemplate.getForObject(eq(uri), eq(String.class))).thenReturn(testString);
        when(objectMapper.readValue(eq(testString), eq(Creditcard.class))).thenReturn(creditcard);

        assertEquals(creditcardService.retrieveCreditcard(creditcard.getId()), creditcard);
    }
}
