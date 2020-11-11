package com.example.but_trucker2.controller.response;

import com.example.but_trucker2.entity.*;
import com.example.but_trucker2.entity.dto.*;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

public class CommentResponse{

    protected Long id;
    protected String text;
    protected Date date;

    protected ErrorResponse error;
    protected UserResponse developer;

    public CommentResponse(CommentEntity comment) {
        this.id = comment.getId();
        this.text = comment.getText();
        this.date = comment.getDate();

        this.error = new ErrorResponse(comment.getError());
        this.developer = new UserResponse(comment.getDeveloper());
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    public ErrorResponse getError() {
        return error;
    }

    public UserResponse getDeveloper() {
        return developer;
    }
}
