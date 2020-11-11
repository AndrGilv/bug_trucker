package com.example.but_trucker2.controller.request;

import com.example.but_trucker2.entity.ErrorEntity;
import com.example.but_trucker2.entity.UserEntity;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResponsibleDeveloperRequest {

    private Long id;
    private Date date;
    private Long developerId;
    private Long errorId;

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Long getDeveloperId() {
        return developerId;
    }

    public Long getErrorId() {
        return errorId;
    }
}
