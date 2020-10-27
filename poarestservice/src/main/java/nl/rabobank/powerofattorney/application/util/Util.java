package nl.rabobank.powerofattorney.application.util;

import nl.rabobank.powerofattorney.application.exception.RestTemplateErrorHandler;
import org.springframework.web.client.RestTemplate;

public class Util {

    public static String getJsonMessage(String uri) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new RestTemplateErrorHandler());
        return restTemplate.getForObject(uri, String.class);
    }

}
