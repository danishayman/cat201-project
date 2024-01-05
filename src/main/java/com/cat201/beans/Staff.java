package com.cat201.beans;

public class Staff extends User {

    public Staff() {
        super();
    }

    public Staff(int id, String firstName, String lastName, String email, String password, String phone,
            String address, String postalCode) {
        super(id, firstName, lastName, phone, address, postalCode, email, password);
    }
}
