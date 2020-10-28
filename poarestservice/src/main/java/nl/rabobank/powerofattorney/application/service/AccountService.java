package nl.rabobank.powerofattorney.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import nl.rabobank.powerofattorney.application.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class AccountService {

    @Autowired
    @Qualifier("jsonMapper")
    ObjectMapper objectMapper;

    @Autowired
    RestTemplate restTemplate;

    public Account retrieveAccount(String accountId) throws Exception {
        final String uri = "http://localhost:8080/accounts/" + accountId;
        log.info("Attempting to retrieve account with id: " + accountId);
        String result = restTemplate.getForObject(uri, String.class);
        Account account = objectMapper.readValue(result, Account.class);

        return account;
    }
}
