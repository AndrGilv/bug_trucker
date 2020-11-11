package com.example.but_trucker2.controller.response;

import com.example.but_trucker2.entity.ErrorEntity;
import com.example.but_trucker2.entity.ErrorFixingResponsibleAssigningHistoryEntity;
import com.example.but_trucker2.entity.UserEntity;

import javax.persistence.Column;
import java.util.Date;

public class ErrorFixingResponsibleAssigningHistoryResponse {

    private Long id;
    private Date date;

    private UserResponse developer;
    private ErrorResponse error;

    public ErrorFixingResponsibleAssigningHistoryResponse(ErrorFixingResponsibleAssigningHistoryEntity entity) {
        this.id = entity.getId();
        this.date = entity.getDate();

        this.developer = new UserResponse(entity.getDeveloper());
        this.error = new ErrorResponse(entity.getError());
    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public UserResponse getDeveloper() {
        return developer;
    }

    public ErrorResponse getError() {
        return error;
    }


}
