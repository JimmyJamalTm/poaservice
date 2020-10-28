package nl.rabobank.powerofattorney.application.config;

import lombok.Data;

@Data
public class AuthenticationBean {

    private String message;

    public AuthenticationBean(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("PoA App [message=%s]", message);
    }
}