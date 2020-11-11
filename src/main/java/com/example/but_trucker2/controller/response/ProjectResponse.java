package com.example.but_trucker2.controller.response;

import com.example.but_trucker2.entity.ProjectEntity;
import com.example.but_trucker2.entity.UserEntity;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

public class ProjectResponse {

    private Long id;
    private String name;
    private UserResponse manager;

    public ProjectResponse(ProjectEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.manager = new UserResponse(entity.getManager());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public UserResponse getManager() {
        return manager;
    }
}
