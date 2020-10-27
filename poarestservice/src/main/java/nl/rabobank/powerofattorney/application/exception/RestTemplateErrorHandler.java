package nl.rabobank.powerofattorney.application.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

@Slf4j
public class RestTemplateErrorHandler extends DefaultResponseErrorHandler {

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
            log.error("Invalid information in response, status code: " + clientHttpResponse.getStatusCode());
            super.handleError(clientHttpResponse);
    }
}
