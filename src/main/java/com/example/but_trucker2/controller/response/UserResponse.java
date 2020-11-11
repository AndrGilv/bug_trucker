package com.example.but_trucker2.controller.response;

import com.example.but_trucker2.entity.*;
import com.example.but_trucker2.entity.dto.*;

import java.util.List;

public class UserResponse{
    protected String firstname;
    protected String lastname;
    protected String middleName;
    protected String email;
    protected String specialisation;
    protected String username;
    //protected String password;
    protected List<String> roles;
    protected Long id;

    public UserResponse(UserEntity user) {
        this.id = user.getId();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.middleName = user.getMiddleName();
        this.email = user.getEmail();
        this.specialisation = user.getSpecialisation();
        this.username = user.getUsername();
        //this.password = user.getPassword();
        this.roles = user.getRoles();
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getEmail() {
        return email;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public Long getId() {
        return id;
    }

    /*public String getPassword() {
        return password;
    }*/
}

