package com.example.but_trucker2.controller.response.extended;

import com.example.but_trucker2.controller.response.ErrorResponse;
import com.example.but_trucker2.controller.response.ProjectResponse;
import com.example.but_trucker2.controller.response.UserResponse;
import com.example.but_trucker2.entity.ErrorEntity;
import com.example.but_trucker2.entity.ProjectEntity;
import com.example.but_trucker2.entity.UserEntity;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExtendedProjectResponse extends ProjectResponse {

    private List<ErrorResponse> errors;
    private List<UserResponse> developersGroup;

    public ExtendedProjectResponse(ProjectEntity entity) {
        super(entity);
        this.errors = entity.getErrors().stream().map(ErrorResponse::new).collect(Collectors.toList());
        this.developersGroup = entity.getDevelopersGroup().stream().map(UserResponse::new).collect(Collectors.toList());
    }

    public List<ErrorResponse> getErrors() {
        return errors;
    }

    public List<UserResponse> getDevelopersGroup() {
        return developersGroup;
    }
}
