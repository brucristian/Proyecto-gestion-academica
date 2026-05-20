package module.authentication.model;

import shared.enums.Role;

public class User {

    //================// Atributos //================//
    private final long userId;
    private final String firstName;
    private final String lastName;
    private final Password password;
    private final Role role;

    //================// Constructores //================//
    public User(long userId, String firstName, String lastName, Password password, Role role) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
    }

    //================// Setters y Getters //================//


    public long getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Password getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

}
