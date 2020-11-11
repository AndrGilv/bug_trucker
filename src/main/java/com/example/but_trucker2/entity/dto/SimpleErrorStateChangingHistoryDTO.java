package com.example.but_trucker2.entity.dto;

import com.example.but_trucker2.entity.ErrorEntity;
import com.example.but_trucker2.entity.UserEntity;
import com.example.but_trucker2.entity.termsDirectories.ErrorState;

import java.util.Date;

public interface SimpleErrorStateChangingHistoryDTO {

    public Date getChangeDate();
    //public void setChangeDate(Date changeDate);


    public ErrorState getState();
    //public void setState(ErrorState state);


}
