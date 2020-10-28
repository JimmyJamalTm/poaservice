package nl.rabobank.powerofattorney.application.model;

public enum Role {
    ADMIN,
    USER;

    @Override
    public String toString() {
        return name();
    }
}
