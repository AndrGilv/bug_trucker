package com.example.but_trucker2.controller.request;

import com.example.but_trucker2.entity.ErrorEntity;
import com.example.but_trucker2.entity.UserEntity;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentsRequest {

    private Long id;
    private String text;
    private Date date;
    private Long errorId;
    private Long developerId;

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    public Long getErrorId() {
        return errorId;
    }

    public Long getDeveloperId() {
        return developerId;
    }
}
