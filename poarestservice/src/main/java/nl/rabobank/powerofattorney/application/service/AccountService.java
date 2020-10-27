package nl.rabobank.powerofattorney.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.rabobank.powerofattorney.application.model.Account;
import nl.rabobank.powerofattorney.application.util.Util;

public class AccountService {

    ObjectMapper objectMapper = new ObjectMapper();

    public Account retrieveAccount(String accountId) throws Exception {
        // TODO : error handling
        final String uri = "http://localhost:8080/accounts/" + accountId;
        String result = Util.getJsonMessage(uri);
        System.out.println(result);
        Account account = objectMapper.readValue(result, Account.class);

        return account;

    }



}
