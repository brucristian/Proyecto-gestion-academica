package org.unischeduler.backend.domain.model.auth.entity;

import org.unischeduler.backend.domain.model.auth.enums.DocumentType;
import org.unischeduler.backend.domain.model.auth.enums.Gender;
import org.unischeduler.backend.domain.model.auth.enums.Role;
import org.unischeduler.backend.domain.model.auth.enums.Status;
import org.unischeduler.backend.domain.model.auth.vo.Email;
import org.unischeduler.backend.domain.model.auth.vo.EncodedPassword;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class User {
    // Identificación
    private final String userId;

    // Información personal
    private final String firstName;
    private final String lastName;
    private DocumentType documentType;
    private final String documentNumber;
    private final LocalDate birthDate;
    private final Gender gender;

    // Contacto
    private String phoneNumber;
    private String address;
    private final Email email;

    // Seguridad
    private EncodedPassword password;

    // Sistema
    private Status status;
    private final Role role;
    private final LocalDateTime createdAt;

    public User(String userId, String firstName, String lastName,
                DocumentType documentType, String documentNumber, LocalDate birthDate,
                Gender gender, String phoneNumber, String address,
                Email email, EncodedPassword password, Status status, Role role, LocalDateTime createdAt) {


        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.password = password;
        this.status = status;
        this.role = role != null ? role : Role.STUDENT;
        this.createdAt = createdAt != null? createdAt : LocalDateTime.now();
    }

    public String getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public Email getEmail() {
        return email;
    }

    public EncodedPassword getPassword() {
        return password;
    }

    public Status getStatus() {
        return status;
    }

    public Role getRole() {
        return role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // Setters

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPassword(EncodedPassword password) {
        this.password = password;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
