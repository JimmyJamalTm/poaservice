package nl.rabobank.powerofattorney.application.util;

import org.springframework.web.client.RestTemplate;

public class Util {

    public static String getJsonMessage(String uri) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, String.class);
    }

}
