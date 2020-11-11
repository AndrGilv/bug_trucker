package com.example.but_trucker2.controller.response.extended;


import com.example.but_trucker2.controller.response.*;
import com.example.but_trucker2.entity.*;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExtendedUserResponse extends UserResponse {


    private List<ErrorStateChangingHistoryResponse> errorHistory;
    private List<ProjectResponse> projects;
    private List<ErrorResponse> errorsFoundList;
    private List<ProjectResponse> projectsManagedList;
    private List<CommentResponse> commentsList;
    private List<ErrorFixingResponsibleAssigningHistoryResponse> responsibleErrorsList;

    public ExtendedUserResponse(UserEntity entity) {
        super(entity);
        this.errorHistory = entity
                .getErrorHistory()
                .stream()
                .map(ErrorStateChangingHistoryResponse::new)
                .collect(Collectors.toList());
        this.projects = entity.getProjects().stream().map(ProjectResponse::new).collect(Collectors.toList());;
        this.errorsFoundList = entity.getErrorsFoundList().stream().map(ErrorResponse::new).collect(Collectors.toList());;
        this.projectsManagedList = entity.getProjectsManagedList().stream().map(ProjectResponse::new).collect(Collectors.toList());;
        this.commentsList = entity.getCommentsList().stream().map(CommentResponse::new).collect(Collectors.toList());;
        this.responsibleErrorsList = entity.getResponsibleErrorsList().stream().map(ErrorFixingResponsibleAssigningHistoryResponse::new).collect(Collectors.toList());;
    }

    public List<ErrorStateChangingHistoryResponse> getErrorHistory() {
        return errorHistory;
    }

    public List<ProjectResponse> getProjects() {
        return projects;
    }

    public List<ErrorResponse> getErrorsFoundList() {
        return errorsFoundList;
    }

    public List<ProjectResponse> getProjectsManagedList() {
        return projectsManagedList;
    }

    public List<CommentResponse> getCommentsList() {
        return commentsList;
    }

    public List<ErrorFixingResponsibleAssigningHistoryResponse> getResponsibleErrorsList() {
        return responsibleErrorsList;
    }
}