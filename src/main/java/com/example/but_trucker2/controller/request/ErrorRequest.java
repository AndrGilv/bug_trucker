package com.example.but_trucker2.controller.request;

import com.example.but_trucker2.entity.*;
import com.example.but_trucker2.entity.termsDirectories.ErrorCriticality;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public class ErrorRequest {

    private Long id;
    private String name;
    private String description;
    private String occurrenceAre;
    private int criticalityId;
    private Long projectId;
    private Long errorFounderUserId;
    private List<Long> errorHistoryIdList = new ArrayList<>();
    private List<Long> commentsIdList = new ArrayList<>();
    private List<Long> responsibleHistoryIdList = new ArrayList<>();

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

    public int getCriticalityId() {
        return criticalityId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public Long getErrorFounderUserId() {
        return errorFounderUserId;
    }

    public List<Long> getErrorHistoryIdList() {
        return errorHistoryIdList;
    }

    public List<Long> getCommentsIdList() {
        return commentsIdList;
    }

    public List<Long> getResponsibleHistoryIdList() {
        return responsibleHistoryIdList;
    }
}
