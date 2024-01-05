package com.cat201.beans;

public class User {
    private int id;
    private String firstName;
    private String lastName;

    private String phone;
    private String address;
    private String postalCode;

    private String email;
    private String password;

    public User() {
    }

    public User(int id, String firstName, String lastName, String phone, String address, String postalCode, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.postalCode = postalCode;
        this.email = email;
        this.password = password;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString(){
        String str = "User: " + this.id + " " + this.firstName + " " + this.lastName + " " + this.phone + " " + this.address + " " + this.postalCode + " " + " " + this.email + " " + this.password;
        return str;
    }
}