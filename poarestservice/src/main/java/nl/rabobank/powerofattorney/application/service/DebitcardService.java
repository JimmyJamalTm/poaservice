package nl.rabobank.powerofattorney.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import lombok.extern.slf4j.Slf4j;
import nl.rabobank.powerofattorney.application.exception.UnMarshallingErrorHandler;
import nl.rabobank.powerofattorney.application.model.Debitcard;
import nl.rabobank.powerofattorney.application.util.Util;

@Slf4j
public class DebitcardService {

    ObjectMapper objectMapper = new ObjectMapper();
    DeserializationProblemHandler deserializationProblemHandler = new UnMarshallingErrorHandler();

    public Debitcard retrieveDebitcard(String cardId) throws Exception {
        final String uri = "http://localhost:8080//debit-cards/" + cardId;
        log.info("Attempting to retrieve debitcard with id: " + cardId);
        String result = Util.getJsonMessage(uri);
        objectMapper.addHandler(deserializationProblemHandler);
        Debitcard debitcard = objectMapper.readValue(result, Debitcard.class);

        return debitcard;

    }



}
