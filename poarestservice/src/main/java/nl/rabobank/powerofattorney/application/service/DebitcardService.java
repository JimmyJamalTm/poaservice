package nl.rabobank.powerofattorney.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import nl.rabobank.powerofattorney.application.model.Debitcard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class DebitcardService {

    @Autowired
    @Qualifier("jsonMapper")
    ObjectMapper objectMapper;

    @Autowired
    RestTemplate restTemplate;

    public Debitcard retrieveDebitcard(String cardId) throws Exception {
        final String uri = "http://localhost:8080//debit-cards/" + cardId;
//        log.info("Attempting to retrieve debitcard with id: " + cardId);
        String result = restTemplate.getForObject(uri, String.class);
        Debitcard debitcard = objectMapper.readValue(result, Debitcard.class);

        return debitcard;
    }
}
