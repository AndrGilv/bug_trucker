package com.example.but_trucker2.controller.response;

import com.example.but_trucker2.entity.ErrorEntity;
import com.example.but_trucker2.entity.ErrorStateChangingHistoryEntity;
import com.example.but_trucker2.entity.UserEntity;
import com.example.but_trucker2.entity.termsDirectories.ErrorState;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

public class ErrorStateChangingHistoryResponse {

    private Long id;
    private UserResponse developer;
    private ErrorState state;
    private ErrorResponse error;
    private Date changeDate;

    public ErrorStateChangingHistoryResponse(ErrorStateChangingHistoryEntity entity) {
        this.id = entity.getId();
        this.developer = new UserResponse(entity.getDeveloper());
        this.state = entity.getState();
        this.error = new ErrorResponse(entity.getError());
        this.changeDate = entity.getChangeDate();
    }

    public UserResponse getDeveloper() {
        return developer;
    }

    public ErrorState getState() {
        return state;
    }

    public ErrorResponse getError() {
        return error;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public Long getId() {
        return id;
    }
}
