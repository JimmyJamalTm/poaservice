package nl.rabobank.powerofattorney.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import lombok.extern.slf4j.Slf4j;
import nl.rabobank.powerofattorney.application.exception.UnMarshallingErrorHandler;
import nl.rabobank.powerofattorney.application.model.Creditcard;
import nl.rabobank.powerofattorney.application.util.Util;

@Slf4j
public class CreditcardService {

    ObjectMapper objectMapper = new ObjectMapper();
    DeserializationProblemHandler deserializationProblemHandler = new UnMarshallingErrorHandler();

    public Creditcard retrieveCreditcard(String cardId) throws Exception {
        final String uri = "http://localhost:8080/credit-cards/" + cardId;
        log.info("Attempting to retrieve creditcard with id: " + cardId);
        String result = Util.getJsonMessage(uri);
        objectMapper.addHandler(deserializationProblemHandler);
        Creditcard creditcard = objectMapper.readValue(result, Creditcard.class);
        return creditcard;
    }



}
