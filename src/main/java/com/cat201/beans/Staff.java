package com.cat201.beans;

public class Staff extends User {

    public Staff() {
        super();
    }

    public Staff(int id, String firstName, String lastName, String email, String password, String phone,
            String address) {
        super(id, firstName, lastName, phone, address, email, password);
    }
}
