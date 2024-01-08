package com.cat201.manager;

import com.cat201.beans.Admin;
import com.cat201.beans.Staff;

public class UserManager {
    private static UserManager instance;
    private Staff staff;
    private Admin admin;

    private UserManager() {
    }

    private <T> UserManager(String role, T data) {
        if(role.equals("staff")) {
            this.staff = (Staff) data;
        } else if(role.equals("admin")) {
            this.admin = (Admin) data;
        }
    }

    public static synchronized UserManager get() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public <T> void setUser(String role, T data) {
        if(role.equals("staff")) {
            this.staff = (Staff) data;
        } else if(role.equals("admin")) {
            this.admin = (Admin) data;
        }
    }

    public Object getData(String userType) {
        if ("Staff".equals(userType)) {
            return staff;
        } else if ("Admin".equals(userType)) {
            return admin;
        }
        return null; // Handle unsupported userType
    }
}
