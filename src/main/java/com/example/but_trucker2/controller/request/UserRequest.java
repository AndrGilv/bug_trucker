package com.example.but_trucker2.controller.request;

import com.example.but_trucker2.entity.*;
import lombok.Builder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class UserRequest {

    //private Long id;
    private String firstname;
    private String lastname;
    private String middleName;
    private String email;
    private String specialisation;
    private String username;
    private String password;
    private List<String> roles = new ArrayList<>();

   /*
    private List<Long> errorHistoryIdList = new ArrayList<>();
    private List<Long> projectsId = new ArrayList<>();
    private List<Long> errorsFoundListId = new ArrayList<>();
    private List<Long> projectsManagedListId = new ArrayList<>();
    private List<Long> commentsIdList = new ArrayList<>();
    private List<Long> responsibleDeveloperIdList = new ArrayList<>();*/

   /* public Long getId() {
        return id;
    }*/

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

    public String getPassword() {
        return password;
    }

    public List<String> getRoles() {
        return roles;
    }
/*
    public List<Long> getErrorHistoryIdList() {
        return errorHistoryIdList;
    }

    public List<Long> getProjectsId() {
        return projectsId;
    }

    public List<Long> getErrorsFoundListId() {
        return errorsFoundListId;
    }

    public List<Long> getProjectsManagedListId() {
        return projectsManagedListId;
    }

    public List<Long> getCommentsIdList() {
        return commentsIdList;
    }

    public List<Long> getResponsibleDeveloperIdList() {
        return responsibleDeveloperIdList;
    }*/
}
