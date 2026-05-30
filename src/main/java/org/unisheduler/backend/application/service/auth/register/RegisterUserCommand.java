package org.unisheduler.backend.application.service.auth.register;

public class RegisterUserCommand {
    private final String firstName;
    private final String lastName;
    private final String password;
    private final String role;

    public RegisterUserCommand(String firstName, String lastName, String password, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
