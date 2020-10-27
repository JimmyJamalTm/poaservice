package nl.rabobank.powerofattorney.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import lombok.extern.slf4j.Slf4j;
import nl.rabobank.powerofattorney.application.exception.UnMarshallingErrorHandler;
import nl.rabobank.powerofattorney.application.model.Account;
import nl.rabobank.powerofattorney.application.util.Util;

@Slf4j
public class AccountService {

    ObjectMapper objectMapper = new ObjectMapper();
    DeserializationProblemHandler deserializationProblemHandler = new UnMarshallingErrorHandler();

    public Account retrieveAccount(String accountId) throws Exception {
        final String uri = "http://localhost:8080/accounts/" + accountId;
        log.info("Attempting to retrieve account with id: " + accountId);
        String result = Util.getJsonMessage(uri);
        objectMapper.addHandler(deserializationProblemHandler);
        Account account = objectMapper.readValue(result, Account.class);

        return account;

    }



}
