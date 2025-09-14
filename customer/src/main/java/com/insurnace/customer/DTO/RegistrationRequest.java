package com.insurnace.customer.DTO;

import org.springframework.stereotype.Component;

@Component
public class RegistrationRequest {
private String name;
    private String email;
    private String phone;
    private String password;
    private String gender;
    private String dateOfBirth;
    // Constructors, getters, and setters
    public RegistrationRequest() {
    }
    public RegistrationRequest(String name, String email, String phone, String
    password, String gender, String dateOfBirth) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.gender = gender;   
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


}
