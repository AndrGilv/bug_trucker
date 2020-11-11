package com.example.but_trucker2.entity.dto;

import com.example.but_trucker2.entity.ErrorEntity;
import com.example.but_trucker2.entity.UserEntity;
import com.example.but_trucker2.entity.termsDirectories.ErrorState;

import java.util.Date;
import java.util.List;

public interface ErrorStateChangingHistoryDTO extends SimpleErrorStateChangingHistoryDTO {

    public UserDTO getDeveloper();
    //public void setDeveloper(UserEntity user);

    public SimpleErrorDTO getError();
    //public void setError(ErrorEntity error);

}
