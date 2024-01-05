package com.cat201.beans;

public class Client {
    private int id; 
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String createdBy;

    public Client() {
    }

    public Client(int id, String firstName, String lastName, String phone, String email, String createdBy) {
        this.id = id; 
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.createdBy = createdBy;
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

    public String getPhone(){
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    // Setters

    public void setId(int id) {
        this.id = id; 
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setCreatedBy(String createdBy){
        this.createdBy = createdBy;
    }

    @Override   
    public String toString() {
        return "Client [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone + ", email=" + email + ", createdBy=" + createdBy + "]";
    }
}
