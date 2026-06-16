package org.unischeduler.backend.infrastructure.in.ui.dtos;

import java.time.LocalDate;

public class RegisterStudentRequest {
    private final String firstName;
    private final String lastName;
    private final String documentType;
    private final String documentNumber;
    private final LocalDate birthDate;
    private final String gender;

    // Contacto
    private final String phoneNumber;
    private final String address;
    private final String email;

    private final String academicProgramId;
    private final int initialSemester;
    private final LocalDate admissionDate;
    private final String status;

    public RegisterStudentRequest(String firstName, String lastName, String documentType, String documentNumber,
                                  LocalDate birthDate, String gender, String phoneNumber, String address,
                                  String email, String academicProgramId, int initialSemester, LocalDate admissionDate, String status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.academicProgramId = academicProgramId;
        this.initialSemester = initialSemester;
        this.admissionDate = admissionDate;
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDocumentType() {
        return documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getAcademicProgramId() {
        return academicProgramId;
    }

    public int getInitialSemester() {
        return initialSemester;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public String getStatus() {
        return status;
    }
}
