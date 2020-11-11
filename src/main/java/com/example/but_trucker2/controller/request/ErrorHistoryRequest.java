package com.example.but_trucker2.controller.request;

import com.example.but_trucker2.entity.ErrorEntity;
import com.example.but_trucker2.entity.UserEntity;
import com.example.but_trucker2.entity.termsDirectories.ErrorState;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ErrorHistoryRequest {

    private Long id;
    private Long developerId;
    private int stateId;
    private Long errorId;
    private Date changeDate;

    public Long getId() {
        return id;
    }

    public Long getDeveloperId() {
        return developerId;
    }

    public int getStateId() {
        return stateId;
    }

    public Long getErrorId() {
        return errorId;
    }

    public Date getChangeDate() {
        return changeDate;
    }
}
