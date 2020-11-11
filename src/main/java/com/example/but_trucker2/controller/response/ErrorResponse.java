package com.example.but_trucker2.controller.response;

import com.example.but_trucker2.entity.ErrorEntity;
import com.example.but_trucker2.entity.ProjectEntity;
import com.example.but_trucker2.entity.UserEntity;
import com.example.but_trucker2.entity.termsDirectories.ErrorCriticality;

import javax.persistence.Column;

public class ErrorResponse {

    private Long id;
    private String name;
    private String description;
    private String occurrenceAre;
    private ErrorCriticality criticality;

    private ProjectResponse project;
    private UserResponse errorFounderUser;

    public ErrorResponse(ErrorEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.occurrenceAre = entity.getOccurrenceAre();
        this.criticality = entity.getCriticality();

        this.project = new ProjectResponse(entity.getProject());
        this.errorFounderUser = new UserResponse(entity.getErrorFounderUser());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getOccurrenceAre() {
        return occurrenceAre;
    }

    public ErrorCriticality getCriticality() {
        return criticality;
    }

    public ProjectResponse getProject() {
        return project;
    }

    public UserResponse getErrorFounderUser() {
        return errorFounderUser;
    }
}
